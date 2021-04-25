package com.demirci.note.db.model

import com.demirci.note.R

class NoteState {
    companion object{
        val TODO = 0
        val BEING_DONE = 1
        val DONE = 2

        fun getState(state : Int) : String{
            if(state == TODO){
                return "TO DO";
            }else if(state == BEING_DONE){
                return "BEING DONE";
            }else if(state == DONE){
                return "DONE";
            }
            return ""
        }

        fun getColor(state : Int) : Int{
            if(state == TODO){
                return R.color.to_do
            }else if(state == BEING_DONE){
                return R.color.being_done
            }else if(state == DONE){
                return R.color.done
            }
            return 0
        }
    }

}