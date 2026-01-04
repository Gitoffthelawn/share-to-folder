#!/bin/sh

while read dir size ; do
    convert -resize $size  app/src/main/icons/icon.svg app/src/main/res/$dir/ic_launcher.webp
    convert -resize $size  app/src/main/icons/icon-round.svg app/src/main/res/$dir/ic_launcher_round.webp
done <<EOF
mipmap-mdpi 48
mipmap-hdpi 72
mipmap-xhdpi 96
mipmap-xxhdpi 144
mipmap-xxxhdpi 192
EOF
