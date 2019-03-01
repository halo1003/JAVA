#get highest tags across all branches, not just the current branch
VERSION=`git describe --tags $(git rev-list --tags --max-count=1)`

if [ $? -eq 0 ]
then
    echo "it worked"
else
    echo "it failed"
    VERSION='1.0'
fi

echo "Latest version tag: $VERSION"

remainder="$VERSION"
VNUM1="${remainder%%.*}"; remainder="${remainder#*.}"
VNUM2="${remainder%%.*}"; remainder="${remainder#*.}"

COUNT_OF_COMMIT_MSG_HAVE_SEMVER_MAJOR=`git log -1 --pretty=%B | egrep -c '\+semver:\s?(breaking|major)'`
COUNT_OF_COMMIT_MSG_HAVE_SEMVER_MINOR=`git log -1 --pretty=%B | egrep -c '\+semver:\s?(feature|minor)'`

if [ $COUNT_OF_COMMIT_MSG_HAVE_SEMVER_MAJOR -gt 0 ]; then
    VNUM1=$((VNUM1+1)) 
fi
if [ $COUNT_OF_COMMIT_MSG_HAVE_SEMVER_MINOR -gt 0 ]; then
    VNUM2=$((VNUM2+1)) 
fi

# count all commits for a branch
GIT_COMMIT_COUNT=`git rev-list --count HEAD`
echo "Commit count: $GIT_COMMIT_COUNT" 
export BUILD_NUMBER=$GIT_COMMIT_COUNT

#create new tag
NEW_TAG="$VNUM1.$VNUM2"

echo "Updating $VERSION to $NEW_TAG"

#only tag if commit message have version-bump-message as mentioned above
if [ $COUNT_OF_COMMIT_MSG_HAVE_SEMVER_MAJOR -gt 0 ] ||  [ $COUNT_OF_COMMIT_MSG_HAVE_SEMVER_MINOR -gt 0 ]; then
    echo "Tagged with $NEW_TAG (Ignoring fatal:cannot describe - this means commit is untagged) "
    git tag "$NEW_TAG"
else
    echo "Already a tag on this commit"
fi