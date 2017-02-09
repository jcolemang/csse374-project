total=0
for fname in $(find ./src/ -name "*.java"); do
    echo $(wc -l $fname)
    num=$(cat $fname | wc -l)
    (( total += num ))
done

echo "Total number of lines: " $total

