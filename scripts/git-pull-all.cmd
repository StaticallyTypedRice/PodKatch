@echo off

git checkout master
git pull origin master

git checkout feature-cli
git pull origin feature-cli

git checkout feature-gui
git pull origin feature-gui

git checkout feature-gui-css
git pull origin feature-gui-css

git checkout feature-engine
git pull origin feature-engine
