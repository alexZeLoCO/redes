#! /bin/sh

for FILE in *
do
	if [ -d $FILE ]
	then
		for f in $FILE/*
		do
			if ( echo $f | grep "^.*.md$" ) > /dev/null
			then
				cat $f >> apuntes.md
			fi
		done
	fi
done

exit 0
