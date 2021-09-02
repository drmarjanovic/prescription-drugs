#!/bin/sh
set -e

# Start the container
if [ "$1" = 'prescription-drugs' -a "$(id -u)" = '0' ]; then
    chown -R psw:psw .
    exec su-exec psw "$0" "$@"
fi

exec "$@"
