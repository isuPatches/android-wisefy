#!/bin/zsh

previous_tag=0

writeCommitsBetweenTags()
{
  prettyLogFormat='* %s  newline  [%H](https://github.com/isuPatches/android-wisefy/commit/%H)  newline  %aN | %ai'
  featRegex='\* feat.*'
  fixRegex='\* fix.*'

  featureCommitCount=$(git log "$1..$2" --pretty=format:"* %s" | grep -cE "$featRegex")
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

  otherCommitCount=$(git log "$1..$2" --pretty=format:"* %s" | grep -vcE "\* (feat|fix).*")
  if [ "$otherCommitCount" -ge 1 ]; then
    printf "### Other"
    printf "\n\n%s\n\n" "$(git log "$1..$2" --pretty=format:"$prettyLogFormat" | grep -vE "\* (feat|fix).*" | sed -r 's/newline/\n/g')"
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
