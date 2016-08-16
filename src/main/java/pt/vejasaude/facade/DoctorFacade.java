package pt.vejasaude.facade;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pt.vejasaude.unified.data.CurriculumVitae.CurriculumVitae;
import pt.vejasaude.unified.data.MedicalSpecialty.MedicalSpecialty;
import pt.vejasaude.unified.data.doctor.Doctor;
import pt.vejasaude.unified.data.doctor.IDoctorRespository;
import pt.vejasaude.web.services.doctor.request.CreateNewDoctorRequest;
import pt.vejasaude.web.services.doctor.request.UpdateDoctorRequest;
import pt.vejasaude.web.services.generic.Status;

import javax.print.Doc;
import java.util.Iterator;

/**
 * Created by fmorais on 15/06/2016.
 */
@Service
public class DoctorFacade implements IDoctorFacade{
    @Autowired
    private IDoctorRespository doctorDAO;

    @Override
    public Doctor createDoctor(CreateNewDoctorRequest request, MedicalSpecialty speciality, CurriculumVitae curriculum) {
        Doctor newDoctor = null;
        try{
            newDoctor = new Doctor(request.getName(),speciality,curriculum);
            doctorDAO.save(newDoctor);

        }catch (Exception e){
            e.printStackTrace();
        }
        return newDoctor;
    }

    @Override
    public Iterable<Doctor> getAll(){
        Iterable <Doctor> doctors = null;
        try{
            doctors = doctorDAO.findAll();
        }catch (Exception e){
            e.printStackTrace();
        }
        return doctors;
    }
    @Override
    public Doctor findOne(String id){
        Doctor doctor = null;
        try{
            doctor = doctorDAO.findOne(id);
        }catch (Exception e){
            e.printStackTrace();
        }
        return doctor;
    }

    @Override
    public Doctor updateDoctor(Doctor changes)
    {
        Doctor updateDoctor = null;
        try{
            updateDoctor = new Doctor(changes.getName(),changes.getSpecialty(),changes.getCurriculum());
            updateDoctor = doctorDAO.save(updateDoctor);
        }catch (Exception e){
            e.printStackTrace();
        }
        return updateDoctor;

    }
}
