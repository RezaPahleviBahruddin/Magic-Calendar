# Magic-Calendar
It's our final project of software engineering lessons in College

Displaying calendar and bringing all its special day of Indonesian, such as: Hari Pahlawan, Hari Sumpah Pemuda, and many more. We also provide an academic schedules to this app.

As any other usual calendar app, user can also create their own schedules.


## How to contribute

1. Fork the project from https://github.com/RezaPahleviBahruddin/Magic-Calendar
2. Clone the project to your local environment by using these commands:

    > git clone https://github.com/{YOUR_USERNAME}/Magic-Calendar
    
    > git remote add upstream https://github.com/RezaPahleviBahruddin/Magic-Calendar
    
    Now run `git remote -v` and you will see there are two remotes: 
    * origin
    * upstream
    
    Upstream is the main repository while origin is what you have forked from the main. After you made changes to your local repository, you need to push it to origin. Afterwards, if you want your changes affect the main repository, then, create a pull request.
    
    ### How to commit
    Lets say that we edit the readme.md file. Then, we need to push it to the server.
    
    At first, don't forget to fetch the main project.
    
    > git fetch upstream
    
    check the status (what have you been doing)
    
    > git status
    
    add the files you have changed to the next commit
    
    > git add .
    
    make the commit and give it a message
    
    > git commit -m "THis is your message"
    
    push it to your repository
    
    > git push origin master
    
    after that, open your github. Then, crate a pull request
    
