# git 进阶学习
### stash 

  有时，当你在项目的一部分上已经工作一段时间后，所有东西都进入了混乱的状态，而这时你想要切换到另一个分支做一点别的事情。 问题是，你不想仅仅因为过会儿回到这一点而为做了一半的工作创建一次提交。 针对这个问题的答案是 git stash 命令
* git stash / git stash save 储藏
* git stash list 查看储藏
* git stash apply 拿回最近的储藏/git stash pop 拿回储藏且删掉记录/ git stash apply stash@{0} 拿回第0个储藏/pop同理
* git stash drop stash@{0}  删除某个储藏
* git stash clear 清空储藏
* git stash --keep-index  储藏 not staged（没有暂存跟踪的即 没有 git add .）  的文件
* git stash -u git也会储藏未跟踪的文件
* git stash --patch  提示的过程问我们那些需要储藏
* git stash branch leo  取出stash 并且checkout到leo分支
  ```shell
           git stash list [<options>]
           git stash show [<stash>]
           git stash drop [-q|--quiet] [<stash>]
           git stash ( pop | apply ) [--index] [-q|--quiet] [<stash>]
           git stash branch <branchname> [<stash>]
           git stash save [-p|--patch] [-k|--[no-]keep-index] [-q|--quiet]
                        [-u|--include-untracked] [-a|--all] [<message>]
           git stash [push [-p|--patch] [-k|--[no-]keep-index] [-q|--quiet]
                        [-u|--include-untracked] [-a|--all] [-m|--message <message>]]
                        [--] [<pathspec>...]]
           git stash clear
           git stash create [<message>]
           git stash store [-m|--message <message>] [-q|--quiet] <commit>

    ```
### Git 搜索工具
* git grep -n leo   在文件中搜索leo(可以使用正则)
* git grep --count 
* git grep -p  匹配的行是否属于某个方法或者函数
* git grep --break /--heading 输出更容易阅读的内容
### Git 重写历史
* 修改最后一次提交,修改会导致变基
  ```shell
     git commit --amend 
  ```
* 修改多个提交信息
  ```shell
  $ git rebase -i HEAD~3  //命令
    //vim 编辑
    eidt 0decb04 branch:dev cause:变: solution:done
    pick ffde694 branch:dev cause:变基准备2 solution:done
    pick 7a41e22 branch:dev cause:变基准备3 solution:done
  
    //提示      
    Stopped at 0decb04...  branch:dev cause:变: solution:done
    You can amend the commit now, with   
    git commit --amend   //修改
    Once you are satisfied with your changes, run
    git rebase --continue
    
    
  $ git rebase --amend
    //vim 编辑
    branch:dev
    cause:变     （改为变基准备1）
    solution:done
  
  
  $ git log     //HEAD指针还在这个commit  （0decb04）已经为原commit，已经变基
    commit 541bdf7754efdeb81571b95afafb2a09dc6a5877 (HEAD)   
    Author: leo <357730030@qq.com>
    Date:   Thu Jan 11 19:11:23 2018 +0800
  
    branch:dev
    cause:变基准备1
    solution:done
  $ git rebase --continue    //提交信息
  
  Successfully rebased and updated refs/heads/dev.
  
  ```
* 合并提交请求  
  ```shell
  
  $ git rebase -i HEAD~4  //命令
    //vim编辑
    pick 93de45e branch:dev cause:变基准备1 solution:done 
    s 83b8a59 branch:dev cause:变基准备2 solution:done       //变为s
    s 3d507f6 branch:dev cause:变基准备3 solution:done       // 变为s
    squash bd1811a branch:dev cause:合并准备4 solution:done  //变为s
  
    //vim编辑
    # This is the 1st commit message:
  
    branch:dev
    cause:合并了commit
    solution:done
  
    # This is the commit message #2:
  
    #branch:dev
    #cause:变基准备2
    #solution:done
  
    # This is the commit message #3:
  
    #branch:dev
    #cause:变基准备3
    #solution:done
  
  
    Successfully rebased and updated refs/heads/dev.
  $ git log
    // 查看log 得到   commit 93de45e 变为了  1ef5767
    commit 1ef57674de3ad385266a9b1aa803e7613b895649 (HEAD -> dev)
    Author: leo <357730030@qq.com>
    Date:   Thu Jan 11 19:11:23 2018 +0800
  
      branch:dev
      cause:合并了commit
      solution:done
  
  
  ```  
* 三棵树
  * working directory  工作区  从HEAD checkout 代码  git checkout 
  * Index    stage File  暂存区         //git add 
  * HEAD   将Index区的代码提交到HEAD    //git commit
  