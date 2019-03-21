[中文用户请点](https://github.com/lipses/CarShop/blob/master/README_CN.md)
# CarShop
A simple online automobile sales system which of Data Course Design in Junior

# Browser
Based on Vue.js+ElementUI [Source Code](https://github.com/lipses/CarShop/tree/browser)

# Server
Based on SpringBoot [Source Code](https://github.com/lipses/CarShop/tree/server)

---
## Preparation
You need install node.js and git in your own computer, The project is based on ES2015+, vue, vuex, vue-router, axios and element-ui.Understanding and learning this knowledge in advance will greatly help the use of this project.

## Project Structure
This project has built the following templates, and have built a scaffold based on Vue, which should help you prototyping production-ready admin interfaces. It covers almost everything you need.
```text
├── build                      // webpack config files
├── config                     // main project config
├── src                        // main source code
│   ├── api                    // api service
│   ├── assets                 // module assets like fonts,images (processed by webpack)
│   ├── components             // global components
│   ├── directive              // global directive
│   ├── filters                // global filter
│   ├── icons                  // svg icons
│   ├── mock                   // mock data
│   ├── router                 // router
│   ├── store                  // store
│   ├── styles                 // global css
│   ├── utils                  // global utils
│   ├── views                  // views
│   ├── App.vue                // main app component
│   ├── main.js                // app entry file
│   └── permission.js          // permission authentication
├── static                     // pure static assets (directly copied)
│   └── Tinymce                // rich text editor
├── .babelrc                   // babel config
├── .eslintrc.js               // eslint config
├── .gitignore                 // sensible defaults for gitignore
├── favicon.ico                // favicon ico
├── index.html                 // index.html template
└── package.json               // package.json
```
## Getting started
```text
# clone the project
git clone -b browser https://github.com/lipses/CarShop.git

# install dependency
npm install

# develop
npm run dev
```
This will automatically open [http://localhost:8080](http://localhost:8080).

## License
[MIT](https://github.com/lipses/CarShop/blob/browser/LICENSE)  
Copyright (c) 2017-present lipses
