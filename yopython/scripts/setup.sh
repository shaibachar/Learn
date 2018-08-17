#!/bin/bash
set -e

source ./scripts/util/env.sh
source ./scripts/util/check-os-deps.sh

# backend stuff
# -------------

## Python setup

# create the .env file
if [ ! -f .env ]; then
    echo "* creating initial .env file"
    cp .env.example .env
else
    echo "* .env file already exists"
fi

# create and/or activate the virtualenv and install the requirements
# (keep virtualenv in the project folder's .venv for better shell support)
echo "* install dev requirements"
PIPENV_VENV_IN_PROJECT=1 pipenv install --dev

# create the Procfile.dev file
if [ ! -f Procfile.dev ]; then
    echo "* creating initial Procfile.dev file"
    cp Procfile.dev.example Procfile.dev
else
    echo "* Procfile.dev file already exists"
fi

# create the database
if [ ! -d tmp/postgres ]; then
    echo "* initialising the DB"
    mkdir -p tmp/postgres
    initdb tmp/postgres
    postgres -D tmp/postgres -p $DB_PORT & echo $! > tmp/postgres.pid
    sleep 3
    psql postgres -p $DB_PORT -c "create user ${PROJECT_NAME} with password '${PROJECT_NAME}';"
    psql postgres -p $DB_PORT -c "create database ${PROJECT_NAME} encoding 'utf8' template template0 owner ${PROJECT_NAME};"
    sleep 3
    pipenv run python manage.py migrate
    kill `cat tmp/postgres.pid`
    echo "* DB ready"
else
    echo "* the DB already exists, checking if it needs migrations"
    postgres -D tmp/postgres -p $DB_PORT & echo $! > tmp/postgres.pid
    sleep 3
    pipenv run python manage.py migrate
    kill `cat tmp/postgres.pid`
fi

# frontend stuff
# -------------

if [ ! -d $FRONTEND_NAME ]; then
    create-react-app $FRONTEND_NAME
fi
# build the frontend
(cd $FRONTEND_NAME; npm install)
./scripts/util/prod-package.sh

# link to UI
if [[ ! -e ${PROJECT_NAME}/static && ! -L ${PROJECT_NAME}/static ]]; then
    echo "* linking Django app to the JS frontend"
    CURDIR=`pwd`
    cd ${PROJECT_NAME}
    ln -s ../${FRONTEND_NAME}/build static
    cd $CURDIR
else
    echo "* frontend already linked"
fi

# app-specific
#-------------
./scripts/util/setup-custom.sh

if [ -d .git ]; then
    echo "* initialising the git repo"
    git init
    git add -A
    git commit -m "yo django-rest"
else
    echo "* git repo detected, won't commit anything automatically"
fi

echo "* DONE :)"

exit
