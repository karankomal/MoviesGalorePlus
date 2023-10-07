# Android Project 4 - *Movies Galore+*

Submitted by: **Karan Komal**

**Movies Galore+** is a movie browsing app that allows users to browse the top trending movies, tv shows, and actors of the week.

Time spent: **8.5** hours spent in total

## Required Features

The following **required** functionality is completed:

- [X] **Choose any endpoint on The MovieDB API except `now_playing`**
  - Chosen Endpoint: `Trending Movies`
  - Chosen Endpoint: `Trending TV Shows`
  - Chosen Endpoint: `Trending Actors`
- [X] **Make a request to your chosen endpoint and implement a RecyclerView to display all entries**
- [X] **Use Glide to load and display at least one image per entry**
- [X] **Click on an entry to view specific details about that entry using Intents**

The following **optional** features are implemented:

- [X] **Add another API call and RecyclerView that lets the user interact with different data.** 
- [X] **Add rounded corners to the images using the Glide transformations**
- [X] **Implement a shared element transition when user clicks into the details of a movie**

The following **additional** features are implemented:

- [X] **Nested RecyclerViews to have vertical and horizontal scrolling!**
- [X] **Long on a poster in the Details Activity to be taken to the IMDB page for the item!**


## Video Walkthrough

Here's a walkthrough of implemented user stories:

<img src='walkthrough.gif' title='Video Walkthrough' width='' alt='Video Walkthrough' />

<!-- Replace this with whatever GIF tool you used! -->
GIF created with [ShareX](https://getsharex.com/). 
<!-- Recommended tools:
[Kap](https://getkap.co/) for macOS
[ScreenToGif](https://www.screentogif.com/) for Windows
[peek](https://github.com/phw/peek) for Linux. -->

## Notes

The biggest challenge was by far wrapping my head around how a nested RecyclerView actually works. On top of that, figuring out how to properly give the adapters the required information also took a long time.

## License

    Copyright 2023 Karan Komal

    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

        http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.
