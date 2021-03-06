package org.randi3.edc.service

import java.util.Date
import org.junit.runner.RunWith
import org.randi3.model.{TrialStatus, TrialSubjectIdentificationCreationType, Trial}

import org.scalatest.matchers.MustMatchers
import org.scalatest.FunSpec

import org.scalatest.junit.JUnitRunner
import org.joda.time.LocalDate
import org.randi3.edc.model.openClinica.ConnectionOC
import org.randi3.edc.utility.TestingEnvironmentEDC

@RunWith(classOf[JUnitRunner])
class OpenClinicaServiceSpec extends FunSpec with MustMatchers {

  val connection = new ConnectionOC("http://localhost:8080/OpenClinica-ws", "root", "5baa61e4c9b93f3f0682250b6cf8331b7ee68fd8", 3)
  val service = TestingEnvironmentEDC.openClinicaService

  describe("The OpenClinicaService getTrials method") {



    it("text") {

    }

    it("test1") {
      //println(service.getTrials(connection))
    }

    it("test2") {
      val trials = service.getTrials(connection)
      //println(trials)
      val trialOC = trials.find(trial => trial.identifier != "Test-Studie").get
       //println("-------------- "+trialOC)
      
      val fullTrial = service.getFullTrialOC(trialOC)
      println(fullTrial)
      val trial = Trial(name = trialOC.identifier, abbreviation = "abb" , description = trialOC.identifier, startDate= new LocalDate(), endDate = new LocalDate(), status = TrialStatus.ACTIVE, treatmentArms = Nil, criterions = trialOC.getAllCriteria(), participatingSites = List(), randomizationMethod = None, stages = Map(), identificationCreationType = TrialSubjectIdentificationCreationType.EXTERNAL, isTrialOpen =  false, isStratifiedByTrialSite =  false).toOption.get
  //    println(trial)
  //    val list = service.getSubjectsData(trialOC, 3, List("DS_Patient1"))
//      println(list)
    }

    it("test") {

      val trials = service.getTrials(connection)
      val trialOC = trials.find(trial => trial.identifier != "Test-Studie").get
      //val list = service.getSubjectList(trialOC)
     // println(list)
    }
  }



  describe("The OpenClinicaService getFullTrial method") {

    it("text") {
      val trials = service.getTrials(connection)
      val trialOC = trials.find(trial => trial.identifier != "Test-Studie").get
      val fullTrialOC = service.getFullTrialOC(trialOC)
      //println(fullTrialOC)
    }


  }

  describe("The OpenClinicaService getSubjects method") {

    it("text") {
      val trials = service.getTrials(connection)
      val trialOC = trials.find(trial => trial.identifier != "Test-Studie").get
      val fullTrialOC = service.getFullTrialOC(trialOC) .get
//      val trialSubjects = service.getSubjectList(fullTrialOC)
 //     println(trialSubjects.map(subject => subject.identifier -> subject.properties.size))
   //   val trialSubjectsFull = service.getSubjectsData(fullTrialOC, 3, List())
//     println(trialSubjectsFull.map(subject => subject.identifier -> (subject.trialSite)))
      service.randomizeNewTrialSubjects(trialOC)
    }
  }

}
