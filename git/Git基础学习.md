# Git 基础学习
## Git 简介
* Git 有三种状态     <br />
  * 已提交（committed） 表示数据已经安全的保存在本地数据库中
  * 已修改（modified） 表示修改了文件，但还没保存到数据库中
  * 已暂存（staged）  表示对一个已修改文件的当前版本做了标记，使之包含在下次提交的快照中
* 由此引入 Git 项目的三个工作区域的概念：Git 仓库、工作目录以及暂存区域。

## Git配置
* --system   配置系统级别    （/etc/gitconfig）
* --global   配置全局     （～/.gitconfig)
* --local    配置某个项目   （.git/config)

+ 用户信息
  ```shell
      git config --global user.name "leo"
      git config --global user.email 357730030@qq.com
    ```
- 文本编译器 (默认是vim)
  ```shell
      git config --global core.editor emacs
    ```    

+ 配置信息
  ```shell
      //命令
    git config --local -l
    //信息
    core.repositoryformatversion=0
    core.filemode=true
    core.bare=false
    core.logallrefupdates=true
    core.ignorecase=true
    core.precomposeunicode=true
    remote.origin.url=https://github.com/JuneLeo/groovy_study_leo_demo.git
    remote.origin.fetch=+refs/heads/*:refs/remotes/origin/*
    commit.template=~/.gitmessage
    branch.master.remote=origin
    branch.master.merge=refs/heads/master
    leodeMacBook-Pro:groovy_study_leo_demo leo$ 
    ```
  或者输入
  ```shell
    //命令
    git config --global user.name
    //信息
    leo
    ```
## 获取帮助
* 三种方式
  ```shell
    git help <verb>
    git <verb> --help
    man git-<verb>
  ```
  eg:
  ```shell
    git push --help
    git help push
    //信息
    ……
  ```
## 获取仓库
  两种方式 
  ```shell
  //第一种 本地自己创建，在项目下输入git init   会生成一个 .git 文件
  git init
  //第二种方式 clone 
  git clone https://github.com/JuneLeo/groovy_study_leo_demo.git
  // or 自定义克隆的仓库名字   仓库名字为leo
  git clone https://github.com/JuneLeo/groovy_study_leo_demo.git  leo 
  ``` 
  补充：clone 还支持 ssh模式
  ```shell
  git clone git@github.com:JuneLeo/groovy_study_leo_demo.git
  ```
## 代码提交
* 查看代码状态
  * 查看代码状态
  ```shell
  git status   git status -s(紧凑格式）
  //信息
  On branch dev   //dev分支
  Changes to be committed:   //说明已经在暂存区，已经背跟踪了
    (use "git reset HEAD <file>..." to unstage)   //从暂存区恢复
            //新文件
          new file:   other/git_help.md    
          new file:   src/main/java/com/meituan/leo/GitStudy.java
  
  Changes not staged for commit:  //说明还未提交
    (use "git add <file>..." to update what will be committed)  //提交设置命令
    (use "git checkout -- <file>..." to discard changes in working directory)
            //已修改
          modified:   other/git_help.md

  ```
  * 忽略文件（.gitignore）     <br />
    * 匹配模式可以以（/）开头防止递归
    * 匹配模式可以以（/）结尾指定目录。
    * 要忽略指定模式以外的文件或目录，可以在模式前加上惊叹号（!）取反。
    * 星号（*）匹配零个或多个任意字符。
    * 使用两个星号（*) 表示匹配任意中间目录，比如`a/**/z` 可以匹配 a/z, a/b/z 或 `a/b/c/z`等。
    * [abc] 匹配任何一个列在方括号中的字符（这个例子要么匹配一个 a，要么匹配一个 b，要么匹配一个 c）。
    * 问号（?）只匹配一个任意字符。
    * 如果在方括号中使用短划线分隔两个字符，表示所有在这两个字符范围内的都可以匹配（比如 [0-9] 表示匹配所有 0 到 9 的数字）
  * 查看已暂存和未暂存的修改
    * git diff  查看当前文件和暂存区文件的差异
    * git diff --cached  /git diff --staged   //查看暂存区和最后一个commit的差异  
  * 移除暂存区的文件
    * git rm --cached a.md   //移除暂存区的文件
    * git rm -f a.md //直接永久删除  
    * 支持删除目录下的批量文件     git rm -f a/\*.doc     //删除a目录下.doc格式文件
  * 移动文件
    * git mv a/file_from.txt b/file_to.txt     //将a/file_from.txt移动到b/file_to.txt  
* 提交代码  
  * 提交更新
  ```shell
    //方式一
    git add .   //添加到暂存区
    git commit -m "xxx"   //提交
    //方式二
    git add .
    git commit    //进入vim文本编辑器
    //方式三
    git commit -a -m "xxxx"   //跳过缓存区直接提交
  ```
* 查看提交历史
  * git log  //最简单的查看方式
  * git log -p -2   //-p 用来显示每次提交的内容差异   -2 2个commit信息
  * git log --stat  //每次提交的简略信息
  * git log --pretty=oneline/short/full/fuller
  * git log --pretty=format [地址](https://git-scm.com/book/zh/v2/Git-%E5%9F%BA%E7%A1%80-%E6%9F%A5%E7%9C%8B%E6%8F%90%E4%BA%A4%E5%8E%86%E5%8F%B2)
  * 更多请参看上面的地址   --pretty 可以用于筛选
* 撤销操作  
  * amend  //提交新的东西，将最后一条commit覆盖
    ```shell
    git add .
    git commit --amend
    ```
  * 取消暂存的文件     //在暂存区的时候
    ```shell
    git reset HEAD b/file_from.txt  //移除暂存区
    ```           
  * 撤销对文件的修改   //不在暂存区的时候
    ```shell
    git checkout -- b/file_from.txt // 直接恢复修改记录   
    ```
  * 提醒：对于一些本地修改后的文件，我们每次都需要依赖修改多的文件，但又不能提到服务器的文件，我们应该是将文件移出暂存区
* 远程仓库
  * git remote  origin - 这是 Git 给你克隆的仓库服
    ```shell
    git remote -v  //命令
    //信息
    origin  https://github.com/JuneLeo/groovy_study_leo_demo.git (fetch)
    origin  https://github.com/JuneLeo/groovy_study_leo_demo.git (push)
    
    ```
  * git remote add origin https://github.com/JuneLeo/groovy_study_leo_demo.git  添加一个新的远程仓库名
    * 我们可以尝试创建两个不同的仓库来操作
  * git fetch [remote-name] 从远程仓库拉取
  * 查看远程仓库信息     git remote show origin  
    ```shell
        git remote show origin
        //
        * remote origin
          Fetch URL: https://github.com/JuneLeo/groovy_study_leo_demo.git
          Push  URL: https://github.com/JuneLeo/groovy_study_leo_demo.git
          HEAD branch: master
          Remote branches:
            dev    tracked
            master tracked
          Local refs configured for 'git push':
            dev    pushes to dev    (up to date)
            master pushes to master (up to date)

    ```
  * 远程仓库的移除与重命名
    * 方式一：vim .git/config
    * 方式二：
    ```shell
        git remote rename leo hero  //将leo仓库重命名为hero
        git remote rm hero   //移除hero 
    ```  
* 打标签
  * 列出标签  git tag   git tag -l "v1.1"
  * 创建标签 
    * 附注标签   git tag -a v1.0 -m "leo,git study,version 1.0"
    * 轻量级标签   git tag v1.0
  * 查看标签  git show v1.0 
  * 后期打标签   git tag -a v0.1 8ca5252b9db155cb2976654ed23310a10a2b3a70 -m "git" 
  * 共享标签  git push origin --tags
  * 删除标签  git tag -d v1.0
  * checkout 标签   git checkout -b v1.0 v1.0
* git别名      <br />
  * 方式一： vim打开配置更改
  ```shell
    vim .gitconfig
     
     ...
     
    [alias]
            co = checkout
    ```
  * 方式二：git config --global alias.co checkout
  