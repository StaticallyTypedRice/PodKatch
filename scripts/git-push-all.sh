#!/bin/bash

git checkout master
git push origin master

git checkout feature-cli
git push origin feature-cli

git checkout feature-gui
git push origin feature-gui

git checkout feature-gui-css
git push origin feature-gui-css

git checkout feature-engine
git push origin feature-engine
