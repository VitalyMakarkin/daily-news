# Daily News

## Purpose

Demonstrate development skills:

- using Clean Architecture and Dependency Injection design pattern
- using app navigation with Single Activity architecture
- using network with cache
- using device database

## Requirements

Use tools:

- [Hilt] - dependency injection
- [Retrofit] - network
- [Room] - device database
- [Cicerone] - navigation
- [Timber] - logging

Use web api:

- [News API] - Worldwide news

## Execute

1. Get `API KEY` from [News API]
2. Create file `apikey.properies` in project directory and insert key:

```
NEWSAPI_KEY=<API_KEY>
```

3. Build application
4. Run

## License

```
MIT License

Copyright (c) 2022 Vitaly Makarkin

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
SOFTWARE.
```

[Hilt]: <https://dagger.dev/hilt/>

[Retrofit]: <https://github.com/square/retrofit>

[Room]: <https://developer.android.com/jetpack/androidx/releases/room>

[Cicerone]: <https://github.com/terrakok/Cicerone>

[Timber]: <https://github.com/JakeWharton/timber>

[News API]: <https://newsapi.org/>