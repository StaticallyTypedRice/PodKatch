#!/bin/bash

git checkout master

git checkout feature-cli
git merge master

git checkout feature-gui
git merge master

git checkout feature-engine
git merge master
