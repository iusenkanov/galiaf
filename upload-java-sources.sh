#!/bin/bash

RELEASE="test@1.0.1"
SRC_DIR="src/main/java"

echo "Uploading Java source files to release: $RELEASE"

find "$SRC_DIR" -name "*.java" | while read filepath; do
  # Преобразуем абсолютный путь в путь от корня пакета
  stripped_path=${filepath#"$SRC_DIR/"}

  echo "Uploading $filepath as $stripped_path"
  sentry-cli releases files "$RELEASE" upload "$filepath" "$stripped_path"
done

