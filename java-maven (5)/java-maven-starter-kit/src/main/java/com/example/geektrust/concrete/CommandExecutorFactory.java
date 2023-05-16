package com.example.geektrust.concrete;

import com.example.geektrust.model.Command;
import com.example.geektrust.service.CommandExecutor;

public class CommandExecutionFactory {
    public static CommandExecutor getExecutor(Command task) {
        CommandExecutor executor = null;
        if(task!=null){
            switch(task.getInputCommand()){
                case ADD_COURSE_OFFERING:
                    executor = new AddCourseCommandExecutorImpl();
                    break;
                case CANCEL:
                    executor = new CancelCourseCommandExecutorImpl();
                    break;
                case ALLOT:
                    executor = new AllotCourseCommandExecutorImpl();
                    break;
                case REGISTER:
                    executor = new RegisterCourseCommandExecutorImpl();
                    break;
                default:
                    break;
            }
        }
        return executor;
    }
}