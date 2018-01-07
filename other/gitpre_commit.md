#Git 提交格式
###目的
* 在一些大厂多人开发的项目中，为了统一管理提交信息，以及为了以后爬取数据的方便，我们应该对提交的commit message进行格式统一。
* git commit message 的创建方式，下面我们给出了两种方式创建

###方式
* 方式一：固定模版

  * 创建commit message template， 在根目录下创建.git_common_commit_template,
    * ```shell 
        vim .git_common_commit_template
      ```
  * 然后在vim编译器中键入模版 .wq保存
    * ```shell
        //键入模版
        branch:
        cause:
        solution:
        ```
  * 进入项目目录下，让git 使用模版  （路径与前面所保存的路径一致）
    * ```shell
        git config --local commit.template ~/.git_common_commit_template  
        ``` 
    ![](/image/gitconfig.jpg =300x)
  * 提交commit测试
    * ```shell
        git add .
        git commit
        ```  
    * git commit 信息设置 
    ![](/image/gitcommit.png =400x)
    * git log 日志     <br /> 
    ![](/image/gitlog.jpg =400x)