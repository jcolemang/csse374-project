
for PATHNAME in $(find ./ -name "*.java"); do
    NAME=$(echo $PATHNAME | sed "s/\.\/src\/\(.*\)\/\(.*\).java/\1.\2/")
    echo $NAME
done

