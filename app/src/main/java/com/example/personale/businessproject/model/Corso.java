package com.example.personale.businessproject.model;

/**
 * Created by personale on 19/02/2017.
 */

public enum Corso {
    LTM("LTM"),
    ISA("ISA"),
    SKILLZONE("SKILLZONE");

    private String corso;

    Corso(String tipoCorso){
        this.corso = tipoCorso;
    }

    public String toString(){
        return corso;
    }

    public static Corso setCorso(String tipoCorso){
        try{
            if(tipoCorso.equalsIgnoreCase(Corso.LTM.toString())){
                return Corso.LTM;
            }
            else if(tipoCorso.equalsIgnoreCase(Corso.ISA.toString())){
                return Corso.ISA;
            }
            else if(tipoCorso.equalsIgnoreCase(Corso.SKILLZONE.toString())){
                return Corso.SKILLZONE;
            }
            else{
                throw new CourseNotFoundException();
            }
        }catch (CourseNotFoundException ctfe){
            ctfe.toString();
            return null;
        }

    }
}

class CourseNotFoundException extends Exception{
    public CourseNotFoundException(){}

    public String toString(){
        return "Course not found";
    }
}
