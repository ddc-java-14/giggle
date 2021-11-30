---
title: Overview
description: Project summary.
menu: Overview
order: 0
---

## Summary

This app will take a person's interest in a particular word and associate it with a funny joke. 
When the person clicks on the app, it will automatically ask you for the word of your choosing. When inserted a particular joke will arise. At the same time if the joke arising so will a picture of someone laughing.



## Intended users

* Someone who really could use a laugh!

    > As a person who likes giggle a lot, I want a joke app, so that I can get a laugh anytime I need it.
    
* Anyone who is sad and who could use a picker upper.

    > As a grieving person that just lost a loved one, I need an easy-to-use humor app so that I can ease some of my pain.
 
* An executive at a board meeting needing to break the ice.

    > As an executive who speaks at a lot of meetings, I would like to have a fast an easy joke to begin with.

## Functionality
   
* Enter a word

* Read the joke that pops up for that word.

* Enter a different word and read the joke pertaining to that word.

## Summary of current state

* This app currently returns a joke with the users search word. When the search does not correspond to a joke in the API, no joke appears.

* Aesthetics and cosmetics

  * Could have a picture of a person laughing.
  
  * Sound effect would be laughter coming out of the laughing persons mouth.
  
  * A picture of a cartoon laughing dog icon for the app.
  
* Functional improvements

  * I think it would be nice to say "no joke found" when API doesn't return a joke associated with that word.
  
  * Multiple jokes per search word.  

## Persistent data

* Different jokes, that are your favorites. 
    
## Technical requirements and dependencies

* This app is using google sign in, retrofit, reactivex and gson.

* This app has been tested on Samsung A71 android version 11 and also, a LGQ7+, android version 9.

* The app needed permission to use the internet.

### JokeAPI

* <https://v2.jokeapi.dev/>

* After the user types in the search term, the app will make request of the service for a randomly selected joke using that search term.

* If the service is unavailable, the app will still be able to display saved jokes, but not be able to search for new ones.

## Build instructions

* From the git hub repository click the green code button and make that the ssh is selected. Then click the copy icon.

* Go to intelJ and select new project from  git version control and paste the link and clone the project.

* Then run on the device of your choosing.

## Basic user instructions 

* Enter a word in the search word bar and press the magnifying glass icon for the joke to appear.

* If you want to save the joke, just press the heart for save.

## Copyrights and licences

### Android standard and support libraries, Material Components, Google Sign In, Room

Copyright 2007-present The Android Open Source Project

Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with the License. You may obtain a copy of the License at

http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the specific language governing permissions and limitations under the License.

### ReactiveX: RxJava & RxAndroid

Copyright (c) 2015 - present, RxJava Contributors

Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with the License. You may obtain a copy of the License at

http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the specific language governing permissions and limitations under the License.

### Stetho

MIT License

Copyright (c) Facebook, Inc. and its affiliates.

Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated documentation files (the "Software"), to deal in the Software without restriction, including without limitation the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.

