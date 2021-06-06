package com.ziad.administrateur.inscription.administrative;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import javax.mail.MessagingException;
import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.ziad.enums.Gender;
import com.ziad.exceptions.CSVReaderOException;
import com.ziad.exceptions.DataNotFoundExceptions;
import com.ziad.exceptions.FormatReaderException;
import com.ziad.models.AnneeAcademique;
import com.ziad.models.Etudiant;
import com.ziad.models.Filiere;
import com.ziad.models.InscriptionAdministrative;
import com.ziad.models.InscriptionEnLigne;

@Controller
@RequestMapping("/admin")
public class InscriptionAdministrativeController {
	@Autowired
	private InscritpionAdministrativeInterface inscription_metier;

	private final static String ATTRIBUT_LIST_ETUDIANTS = "etudiants";
	private final static String ATTRIBUT_LIST_FILIERES = "filieres";

	private final static String ATTRIBUT_LIST_SEMESTRES_JSON = "semestresjson";
	private final static String ATTRIBUT_LIST_MODULES_JSON = "modulesjson";

	private final static String ATTRIBUT_LIST_ANNEE_ACADEMIQUES = "annees_academiques";
	private final static String ATTRIBUT_INSCRIPTION_EN_LIGNE = "inscriptionenligne";
	private final static String ATTRIBUT_LIST_INSCRIPTIONS_ADMINISTRATIVES = "InscriptionAssociative";

	@SuppressWarnings("unchecked")
	@GetMapping("/inscription/InscriptionAdministrative/{inscriptionenligne}")
	public ModelAndView InscriptionAdministrative(@PathVariable("inscriptionenligne") Long idInscription)
			throws DataNotFoundExceptions {
		ModelAndView model = new ModelAndView("inscription_administrative/InscriptionAdministrative");
		model.addObject("InscriptionAdministrative", "mm-active");// will be used in the nav-bar
		ArrayList<Object> besoins = inscription_metier.prepareInscriptionDatas();
		model.addObject(ATTRIBUT_LIST_ETUDIANTS, (List<Etudiant>) besoins.get(0));
		model.addObject(ATTRIBUT_LIST_FILIERES, (List<Filiere>) besoins.get(1));
		model.addObject(ATTRIBUT_LIST_ANNEE_ACADEMIQUES, (List<AnneeAcademique>) besoins.get(2));
		model.addObject(ATTRIBUT_INSCRIPTION_EN_LIGNE, idInscription);
		return model;
	}

//---------------------------------action :création d'une nouvelle inscription administrative-----------------------------//

	@PostMapping("/inscription/InscriptionAdministrative/{inscriptionenligne}")
	public ModelAndView createANewInscriptionAdministrative(@RequestParam("annee_academique") Long id_annee_academique,
			@PathVariable("inscriptionenligne") Long id_inscription_en_ligne, @RequestParam("filiere") Long id_filiere,
			@RequestParam("photo") MultipartFile photo, @RequestParam("bac") MultipartFile bac,
			@RequestParam("rn") MultipartFile relevee_de_note, @RequestParam("an") MultipartFile acte_naissance,
			@RequestParam("cin") MultipartFile cin) throws IOException,MessagingException {
		inscription_metier.createInscriptionAdministrative(id_annee_academique, id_inscription_en_ligne, id_filiere,
				photo, bac, relevee_de_note, acte_naissance, cin);
		return new ModelAndView("redirect:/admin/inscription/ListInscriptionAdministrative");
	}

//-----------------------------------action : modifier inscription administrative---------------------------------------//

	@PostMapping("/inscription/ModifierInscriptionAdministrative/{id_filiere}/{id_etudiant}")
	public ModelAndView ModifierInscriptionAdministrative(@RequestParam("last_name_fr") String last_name_fr,
			@RequestParam("last_name_ar") String last_name_ar, @RequestParam("first_name_fr") String first_name_fr,
			@RequestParam("first_name_ar") String first_name_ar, @RequestParam("massar_edu") String massar_edu,
			@RequestParam("cne") String cne, @RequestParam("nationality") String nationality,
			@RequestParam("gender") Gender gender, @RequestParam("birth_date") String birth_date,
			@RequestParam("birth_place") String birth_place, @RequestParam("city") String city,
			@RequestParam("province") String province, @RequestParam("bac_year") Integer bac_year,
			@RequestParam("bac_type") String bac_type, @RequestParam("mention") String mention,
			@RequestParam("high_school") String high_school, @RequestParam("bac_place") String bac_place,
			@RequestParam("academy") String academy, @RequestParam("date_pre_inscription") String date_pre_inscription,
			@RequestParam("date_valid_inscription") String date_valid_inscription,
			@PathVariable("id_etudiant") Long id_etudiant, @PathVariable("id_filiere") Long id_filiere,
			@RequestParam("photo") MultipartFile photo, @RequestParam("bac") MultipartFile bac,
			@RequestParam("rn") MultipartFile relevee_note, @RequestParam("an") MultipartFile acte_de_naissance,
			@RequestParam("cin") MultipartFile cin, @RequestParam("annee_academique") Long id_annee_academique)
			throws IOException {
		inscription_metier.modifierInscriptionAdministrative(last_name_fr, last_name_ar, first_name_fr, first_name_ar,
				massar_edu, cne, nationality, gender, birth_date, birth_place, city, province, bac_year, bac_type,
				mention, high_school, bac_place, academy, date_pre_inscription, date_valid_inscription, id_etudiant,
				id_filiere, photo, bac, relevee_note, acte_de_naissance, cin, id_annee_academique);
		return new ModelAndView("redirect:/admin/inscription/ListInscriptionAdministrative");
	}

//---------------------------------------action : supprimer inscrip administrative----------------------------------------//

	@GetMapping("/inscription/SupprimerInscriptionAdministrative/{id_filiere}/{id_etudiant}")
	public ModelAndView SupprimerInscriptionAdministrative(@PathVariable("id_etudiant") Long id_etudiant,
			@PathVariable("id_filiere") Long id_filiere) throws EntityNotFoundException {
		inscription_metier.deleteInscriptionAdministrative(id_etudiant, id_filiere);
		return new ModelAndView("redirect:/admin/inscription/ListInscriptionAdministrative");
	}

//-----------------------------------------page affichant la liste des inscriptions administratives-------------------------------//

	@SuppressWarnings("unchecked")
	@GetMapping("/inscription/ListInscriptionAdministrative")
	public ModelAndView listInscriptionAdministratives() throws UnsupportedEncodingException, DataNotFoundExceptions {
		ModelAndView model = new ModelAndView("inscription_administrative/ListInscriptionAdministrative");
		ArrayList<Object> list = inscription_metier.listerInscriptionsAdministratives();
		model.addObject("listAdministartive", "mm-active");
		model.addObject(ATTRIBUT_LIST_ANNEE_ACADEMIQUES, (List<AnneeAcademique>) list.get(0));
		model.addObject(ATTRIBUT_LIST_FILIERES, (List<Filiere>) list.get(1));
		model.addObject(ATTRIBUT_LIST_SEMESTRES_JSON, (String) list.get(2));
		model.addObject(ATTRIBUT_LIST_MODULES_JSON, (String) list.get(3));
		List<InscriptionAdministrative> l = (List<InscriptionAdministrative>) list.get(4);
		model.addObject(ATTRIBUT_LIST_INSCRIPTIONS_ADMINISTRATIVES, l);
		return model;
	}

//***************** Filtrage et affichage de la liste ********************************************
	@PostMapping("/inscription/ListInscriptionAdministrative")
	public ModelAndView listInscriptionAdministrative(
			@RequestParam(name = "id_filiere", required = false) Long idFiliere,
			@RequestParam(name = "id_annee_academique", required = false) Long idAnneeAcademique,
			@RequestParam(name = "id_semestre", required = false) Long idSemestre,
			@RequestParam(name = "id_module", required = false) Long idModule)
			throws EntityNotFoundException, DataNotFoundExceptions, UnsupportedEncodingException {
		ModelAndView model = new ModelAndView("inscription_administrative/ListInscriptionAdministrative");
		ArrayList<Object> list = inscription_metier.listerInscriptionsAdministratives();
		model.addObject("listAdministartive", "mm-active");
		model.addObject(ATTRIBUT_LIST_ANNEE_ACADEMIQUES, list.get(0));
		model.addObject(ATTRIBUT_LIST_FILIERES, list.get(1));
		model.addObject(ATTRIBUT_LIST_SEMESTRES_JSON, (String) list.get(2));
		model.addObject(ATTRIBUT_LIST_MODULES_JSON, (String) list.get(3));
		model.addObject(ATTRIBUT_LIST_INSCRIPTIONS_ADMINISTRATIVES, inscription_metier
				.listInscriptionAdministrativeByFilter(idFiliere, idAnneeAcademique, idSemestre, idModule));
		return model;
	}

	@SuppressWarnings("unchecked")
	@GetMapping("/inscription/PageModifierInscriptionAdministrative/{id_filiere}/{id_etudiant}")
	public ModelAndView PageModifierInscriptionAdministrative(@PathVariable("id_filiere") Long id_filiere,
			@PathVariable("id_etudiant") Long id_etudiant) throws UnsupportedEncodingException {
		ArrayList<Object> besoins = inscription_metier.getInscriptionAdministrative(id_filiere, id_etudiant);

		InscriptionAdministrative ia = (InscriptionAdministrative) besoins.get(0);
		ModelAndView model = new ModelAndView("inscription_administrative/ModifierInscriptionAdministrative");
		model.addObject("ListInscriptionAdministartive", "mm-active");
		model.addObject("ia", ia);
		model.addObject("etudiant", (List<InscriptionEnLigne>) besoins.get(1));
		model.addObject("filieres", (List<Filiere>) besoins.get(2));
		model.addObject("annees_academiques", (List<AnneeAcademique>) besoins.get(3));
		model.addObject("countries", besoins.get(4));
		return model;
	}

	@GetMapping("/inscription/uploadinscription")
	public ModelAndView PageUploadInscriptionAdministrative() {
		ModelAndView model = new ModelAndView("inscription_administrative/UploadInscriptionAdministrative");
		model.addObject("InscriptionAdministartive", "mm-active");
		return model;
	}

	@PostMapping("/inscription/uploadinscription")
	public ModelAndView sinscrireparExcel(@RequestParam("excel") MultipartFile file)
			throws FormatReaderException, IOException, CSVReaderOException {
		ModelAndView model = new ModelAndView("inscription_administrative/UploadInscriptionAdministrative");
		inscription_metier.uploadInscriptionAdministrative(file);
		return model;
	}

}
