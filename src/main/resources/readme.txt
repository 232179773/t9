目录机构规划

system系统文件夹（包括java，jsp，js等所有），用户存放项目通用文件
com.t9.system java项目通用文件夹
webapp/system 界面公共文件夹
webapp/system/skin 界面公共资源文件，如果有其他风格，则新建如skin_blue
webapp/system/js 界面公共js文件夹，单个js直接放到该目录，非单个js以包的形式存放

└─src
    ├─main
    │  ├─java
    │  │  └─com
    │  │      └─t9
    │  │          └─system
    │  │              ├─action
    │  │              ├─dao
    │  │              ├─entity
    │  │              ├─service
    │  │              ├─util
    │  │              └─web
    │  ├─resources
    │  └─webapp
    │      ├─system
    │      │  ├─admin
    │      │  ├─js
    │      │  │  └─My97DatePicker
    │      │  │      ├─lang
    │      │  │      └─skin
    │      │  │          ├─default
    │      │  │          └─whyGreen
    │      │  └─skin
    │      │      ├─css
    │      │      └─images
    │      └─WEB-INF
    │          ├─classes
    │          │  └─com
    │          │      └─t9
    │          │          └─system
    │          │              ├─action
    │          │              ├─dao
    │          │              ├─entity
    │          │              ├─service
    │          │              ├─util
    │          │              └─web
    │          ├─config
    │          └─lib
    └─test
        ├─java
        │  └─com
        │      └─t9
        │          └─system
        │              ├─dao
        │              └─util
        └─resources
