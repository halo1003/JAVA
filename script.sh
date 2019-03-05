#get highest tags
VERSION=`git describe --tags $(git rev-list --tags --max-count=1)`

if [ $? -eq 0 ]
then    
    echo "Latest version tag: $VERSION"
else
    VERSION='0.9'
    echo "No lastest version tag found => initial: $VERSION"    
fi

remainder="$VERSION"
VNUM1="${remainder%%.*}"; remainder="${remainder#*.}"
VNUM2="${remainder%%.*}"; remainder="${remainder#*.}"

# count all commits for a branch
GIT_COMMIT=`git rev-parse HEAD`
NEEDS_TAG=`git describe --contains $GIT_COMMIT`

#only tag if commit message have version-bump-message as mentioned above
if [ -z "$NEEDS_TAG" ]; then    
    VNUM2=$((VNUM2+1))     
    if [ $VNUM2 -gt 9 ]; then        
        VNUM2='0'
        VNUM1=$((VNUM1+1)) 
    fi
    #create new tag
    NEW_TAG="$VNUM1.$VNUM2"
    echo "Tagged with $NEW_TAG (Ignoring fatal:cannot describe - this means commit is untagged) "
    git tag $NEW_TAG    
else
    echo "Already a tag on this commit"
fi