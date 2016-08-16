package pt.vejasaude.web.services.medicalSpecialty.request;

/**
 * Created by fmorais on 05/07/2016.
 */
public class CreateNewSpecialtyRequest {


    private int id;
    private String specialty;

    public int getId() {return id;}

    public String getSpecialty() {return specialty;}

    public void setSpecialty(String specialty) {this.specialty = specialty;}
}
