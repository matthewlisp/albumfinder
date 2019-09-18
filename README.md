# Albumfinder

Albumfinder is a utilitary software to programatically search results for album tracks, just using the artist name and the title of the album! it uses the Discogs API behind the scenes and crawler functions at youtube.com

## Usage

Set env variables for your [Discogs API](https://www.discogs.com/developers) keys.

```
$ export DISCOGSKEY="..."
``` 

```
$ export DISCOGSSECRET="..."
``` 

For now, use leiningen to run, soon i'll be updating this repo with the JAR file to run with Java.

```
lein run "nirvana" "nevermind"
``` 

The result will be printed on your terminal, and a JSON file will be written on your current folder.

The output from this example:


```
[
  {
    "thumb": "https://i.ytimg.com/vi/hTWKbfoikeg/hqdefault.jpg?sqp=-oaymwEjCPYBEIoBSFryq4qpAxUIARUAAAAAGAElAADIQj0AgKJDeAE=&rs=AOn4CLDZ9FVVHUgUy57dxdmpNMKMDUhxIg",
    "time": "4:39",
    "title": "Nirvana - Smells Like Teen Spirit (Official Music Video)",
    "link": "/watch?v=hTWKbfoikeg"
  },
  {
    "thumb": "https://i.ytimg.com/vi/PbgKEjNBHqM/hqdefault.jpg?sqp=-oaymwEjCPYBEIoBSFryq4qpAxUIARUAAAAAGAElAADIQj0AgKJDeAE=&rs=AOn4CLBVDi0zYKppcWTiQr4Mn4vnaHEDUA",
    "time": "5:00",
    "title": "Nirvana - In Bloom (Official Video)",
    "link": "/watch?v=PbgKEjNBHqM"
  },
  {
    "thumb": "https://i.ytimg.com/vi/vabnZ9-ex7o/hqdefault.jpg?sqp=-oaymwEjCPYBEIoBSFryq4qpAxUIARUAAAAAGAElAADIQj0AgKJDeAE=&rs=AOn4CLDfYXhFHB5fzVNMS1KxzXiepAv3lg",
    "time": "3:46",
    "title": "Nirvana - Come As You Are",
    "link": "/watch?v=vabnZ9-ex7o"
  },
  {
    "thumb": "https://i.ytimg.com/vi/tGc8jL4dzao/hqdefault.jpg?sqp=-oaymwEjCPYBEIoBSFryq4qpAxUIARUAAAAAGAElAADIQj0AgKJDeAE=&rs=AOn4CLAIHSZqAnHyWD1Hj59oyNaSkMbC4g",
    "time": "3:12",
    "title": "Nirvana - Breed (Live At The Paramount/1991)",
    "link": "/watch?v=tGc8jL4dzao"
  },
  {
    "thumb": "https://i.ytimg.com/vi/pkcJEvMcnEg/hqdefault.jpg?sqp=-oaymwEjCPYBEIoBSFryq4qpAxUIARUAAAAAGAElAADIQj0AgKJDeAE=&rs=AOn4CLBEdB7rG-2tEKEzG9iURcA6eb43Zw",
    "time": "4:16",
    "title": "Nirvana - Lithium",
    "link": "/watch?v=pkcJEvMcnEg"
  },
  {
    "thumb": "https://i.ytimg.com/vi/GLrBhH27DpI/hqdefault.jpg?sqp=-oaymwEjCPYBEIoBSFryq4qpAxUIARUAAAAAGAElAADIQj0AgKJDeAE=&rs=AOn4CLDXtjkAaY5qPzUe6tURNUp0VwrWtA",
    "time": "2:58",
    "title": "Nirvana - Polly",
    "link": "/watch?v=GLrBhH27DpI"
  },
  {
    "thumb": "https://i.ytimg.com/vi/bm6Iz-I5OmQ/hqdefault.jpg?sqp=-oaymwEjCPYBEIoBSFryq4qpAxUIARUAAAAAGAElAADIQj0AgKJDeAE=&rs=AOn4CLDef2Pah1lH523BdNcXP1JA1tFH9A",
    "time": "2:24",
    "title": "Nirvana - Territorial Pissings",
    "link": "/watch?v=bm6Iz-I5OmQ"
  },
  {
    "thumb": "https://i.ytimg.com/vi/wPsJIOOolWU/hqdefault.jpg?sqp=-oaymwEjCPYBEIoBSFryq4qpAxUIARUAAAAAGAElAADIQj0AgKJDeAE=&rs=AOn4CLBy7oS0RHL8NycYwnqv3BHZtkyUBQ",
    "time": "3:45",
    "title": "Nirvana- Drain You [HD]",
    "link": "/watch?v=wPsJIOOolWU"
  },
  {
    "thumb": "https://i.ytimg.com/vi/7xeasq05zxc/hqdefault.jpg?sqp=-oaymwEmCPYBEIoBSFryq4qpAxgIARUAAAAAGAElAADIQj0AgKJDeAGAAQE=&rs=AOn4CLCWkQglAibKK3mbiunfjKbkO-q4cg",
    "time": "2:40",
    "title": "Nirvana - Lounge Act (Live at Reading 1992)",
    "link": "/watch?v=7xeasq05zxc"
  },
  {
    "thumb": "https://i.ytimg.com/vi/AI7-i3UmZ30/hqdefault.jpg?sqp=-oaymwEjCPYBEIoBSFryq4qpAxUIARUAAAAAGAElAADIQj0AgKJDeAE=&rs=AOn4CLCkXZMM7VbqVZ1fwasbnphMKa2apQ",
    "time": "3:38",
    "title": "Stay Away - Nirvana (Nevermind) 1991",
    "link": "/watch?v=AI7-i3UmZ30"
  },
  {
    "thumb": "https://i.ytimg.com/vi/1I1f48NkQCQ/hqdefault.jpg?sqp=-oaymwEjCPYBEIoBSFryq4qpAxUIARUAAAAAGAElAADIQj0AgKJDeAE=&rs=AOn4CLApSAsCv4Hjlm2yi9nd0Hlfs2LDMg",
    "time": "4:00",
    "title": "Nirvana - On A Plain (Legendado) - Acústico/1993",
    "link": "/watch?v=1I1f48NkQCQ"
  },
  {
    "thumb": "https://i.ytimg.com/vi/cVRFC9-VF4A/hqdefault.jpg?sqp=-oaymwEjCPYBEIoBSFryq4qpAxUIARUAAAAAGAElAADIQj0AgKJDeAE=&rs=AOn4CLCunNrr_bdAiBqtM1TreA4M3MQ54A",
    "time": "3:46",
    "title": "Nirvana - Something in the Way [Lyrics]",
    "link": "/watch?v=cVRFC9-VF4A"
  },
  {
    "thumb": "https://i.ytimg.com/vi/kb0A2IVPVrA/hqdefault.jpg?sqp=-oaymwEjCPYBEIoBSFryq4qpAxUIARUAAAAAGAElAADIQj0AgKJDeAE=&rs=AOn4CLB4O-tAc-6XTtJ4WC0Xj6OlQl07Yg",
    "time": "9:10",
    "title": "Nirvana - Untitled 'Lost' Song",
    "link": "/watch?v=kb0A2IVPVrA"
  },
  {
    "thumb": "https://i.ytimg.com/vi/gmtbsFW0tCw/hqdefault.jpg?sqp=-oaymwEjCPYBEIoBSFryq4qpAxUIARUAAAAAGAElAADIQj0AgKJDeAE=&rs=AOn4CLBIyPtaYZGRrOjWqOmRUcCgAeKlvw",
    "time": "6:49",
    "title": "Nirvana - Endless, Nameless [Hidden Track]",
    "link": "/watch?v=gmtbsFW0tCw"
  }
]
``` 

## License

Copyright © 2019 FIXME

This program and the accompanying materials are made available under the
terms of the Eclipse Public License 2.0 which is available at
http://www.eclipse.org/legal/epl-2.0.

This Source Code may also be made available under the following Secondary
Licenses when the conditions for such availability set forth in the Eclipse
Public License, v. 2.0 are satisfied: GNU General Public License as published by
the Free Software Foundation, either version 2 of the License, or (at your
option) any later version, with the GNU Classpath Exception which is available
at https://www.gnu.org/software/classpath/license.html.
