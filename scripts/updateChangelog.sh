#!/bin/zsh

previous_tag=0

writeCommitsBetweenTags()
{
  prettyLogFormat='* %s  newline  [%H](https://github.com/isuPatches/android-wisefy/commit/%H)  newline  %aN | %ai'
  featRegex='\* feat.*'
  fixRegex='\* fix.*'
  releaseRegex='\* release.*'
  choreRegex='\* chore.*'

  featureCommitCount=$(git log "$1..$2" --pretty=format:"* %s" | grep -c "$featRegex")
  if [ "$featureCommitCount" -ge 1 ]; then
    printf "### Features"
    printf "\n\n%s\n\n" "$(
      git log "$1..$2" --pretty=format:"$prettyLogFormat" |
      grep -E "$featRegex" |
      sed -r 's/newline/\n/g' |
      sed "/feat.*!:/s/^*/* **<< BREAKING >>** /"
    )"
  fi

  fixesCommitCount=$(git log "$1..$2" --pretty=format:"* %s" | grep -cE "$fixRegex")
  if [ "$fixesCommitCount" -ge 1 ]; then
    printf "### Bug Fixes"
    printf "\n\n%s\n\n" "$(
      git log "$1..$2" --pretty=format:"$prettyLogFormat" |
      grep -E "$fixRegex" |
      sed -r 's/newline/\n/g' |
      sed "/fix.*!:/s/^*/* **<< BREAKING >>** /"
    )"
  fi

  releasesCommitCount=$(git log "$1..$2" --pretty=format:"* %s" | grep -cE "$releaseRegex")
  if [ "$releasesCommitCount" -ge 1 ]; then
    printf "### Releases"
    printf "\n\n%s\n\n" "$(
      git log "$1..$2" --pretty=format:"$prettyLogFormat" |
      grep -E "$releaseRegex" |
      sed -r 's/newline/\n/g' |
      sed "/release.*!:/s/^*/* **<< BREAKING >>** /"
    )"
  fi

  choreCommitCount=$(git log "$1..$2" --pretty=format:"* %s" | grep -cE "$choreRegex")
  if [ "$choreCommitCount" -ge 1 ]; then
    printf "### Chores"
    printf "\n\n%s\n\n" "$(
      git log "$1..$2" --pretty=format:"$prettyLogFormat" |
      grep -E "$choreRegex" |
      sed -r 's/newline/\n/g' |
      sed "/chore.*!:/s/^*/* **<< BREAKING >>** /"
    )"
  fi

  otherCommitCount=$(git log "$1..$2" --pretty=format:"* %s" | grep -vcE "\* (feat|fix|release|chore).*")
  if [ "$otherCommitCount" -ge 1 ]; then
    printf "### Other"
    printf "\n\n%s\n\n" "$(git log "$1..$2" --pretty=format:"$prettyLogFormat" | grep -vE "\* (release|feat|fix|release|chore).*" | sed -r 's/newline/\n/g')"
  fi
}

printf "## Unreleased"
printf "\n\n"
latestTag=$(git describe --tags --abbrev=0)
writeCommitsBetweenTags "$latestTag" "HEAD"
for current_tag in $(git tag --sort=-creatordate); do
  if [ "$previous_tag" != 0 ];then
    tag_date=$(git log -1 --pretty=format:'%ad' --date=short ${previous_tag})
    printf "## %s (%s)\n\n" "$previous_tag" "$tag_date"
    writeCommitsBetweenTags "$current_tag" "$previous_tag"
  fi
  previous_tag=${current_tag}
done
if [ "$previous_tag" != 0 ];then
  tag_date=$(git log -1 --pretty=format:'%ad' --date=short "${previous_tag}")
  printf "## %s (%s)\n\n" "$previous_tag" "$tag_date"
  writeCommitsBetweenTags "$(git rev-list --max-parents=0 HEAD)" "$previous_tag"
fi
