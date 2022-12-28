#!/bin/zsh

./gradlew wisefy:core:clean
./gradlew wisefy:core:publishReleasePublicationToMavenLocal

./gradlew wisefy:accesspoints:clean
./gradlew wisefy:accesspoints:publishReleasePublicationToMavenLocal

./gradlew wisefy:addnetwork:clean
./gradlew wisefy:addnetwork:publishReleasePublicationToMavenLocal

./gradlew wisefy:networkconnection:clean
./gradlew wisefy:networkconnection:publishReleasePublicationToMavenLocal

./gradlew wisefy:networkinfo:clean
./gradlew wisefy:networkinfo:publishReleasePublicationToMavenLocal

./gradlew wisefy:removenetwork:clean
./gradlew wisefy:removenetwork:publishReleasePublicationToMavenLocal

./gradlew wisefy:savednetworks:clean
./gradlew wisefy:savednetworks:publishReleasePublicationToMavenLocal

./gradlew wisefy:signal:clean
./gradlew wisefy:signal:publishReleasePublicationToMavenLocal

./gradlew wisefy:wifi:clean
./gradlew wisefy:wifi:publishReleasePublicationToMavenLocal

./gradlew wisefy:clean
./gradlew wisefy:publishReleasePublicationToMavenLocal

./gradlew wisefy:ktx:clean
./gradlew wisefy:ktx:publishReleasePublicationToMavenLocal
