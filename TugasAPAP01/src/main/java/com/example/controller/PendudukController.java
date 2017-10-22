package com.example.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;

import com.example.model.PendudukModel;
import com.example.service.PendudukService;
import com.example.model.KeluargaModel;
import com.example.service.KeluargaService;
import com.example.service.AlamatService;

@Controller
public class PendudukController
{
    @Autowired
    PendudukService pendudukDAO;
    @Autowired
    KeluargaService keluargaDAO;
    @Autowired
	AlamatService alamatDAO;
	


    @RequestMapping("/index")
    public String index ()
    {
        return "index";
    }
    
    @RequestMapping("/")
    public String main ()
    {
        return "main";
    }
    
    @RequestMapping("/penduduk")
    public String view (Model model,
    		@RequestParam(value = "nik", required = false, defaultValue="0") String nik)
    {
        PendudukModel penduduk = pendudukDAO.selectPenduduk (nik);

        if (penduduk != null) {
            model.addAttribute ("penduduk", penduduk);
            return "viewPenduduk";
        } else {
            model.addAttribute ("nik", nik);
            return "not-found";
        }   
    }
    
    @RequestMapping("/keluarga")
    public String viewKeluarga (Model model,
    		@RequestParam(value = "nomor_kk", required = false, defaultValue="0") String nomor_kk)
    {
        KeluargaModel keluarga = keluargaDAO.selectKeluarga (nomor_kk);

        if (keluarga != null) {
            model.addAttribute ("keluarga", keluarga);
            return "viewKeluarga";
        } else {
            model.addAttribute ("nomor_kk", nomor_kk);
            return "not-found";
        }   
    }
    
    @RequestMapping("/penduduk/tambah")
    public String addPenduduk (Model model)
    {
        PendudukModel penduduk = new PendudukModel ();
        model.addAttribute ("penduduk", penduduk);
        return "addPenduduk";
    }
    
    @RequestMapping(value = "/penduduk/tambah", method = RequestMethod.POST)
    public String addPendudukSubmit(Model model, @ModelAttribute PendudukModel penduduk)
    {
    	penduduk.generateNik(keluargaDAO, pendudukDAO);
    	pendudukDAO.addPenduduk(penduduk);
        model.addAttribute("nik",penduduk.getNik() );
        return "successAdd";
        
    }
    
    @RequestMapping("/keluarga/tambah")
    public String addKeluarga (Model model)
    {
    	KeluargaModel keluarga = new KeluargaModel ();
    	model.addAttribute ("keluarga", keluarga);
        model.addAttribute ("kelurahans", alamatDAO.selectKelurahans());
        return "addKeluarga";
    }
    
    @RequestMapping(value = "/keluarga/tambah", method = RequestMethod.POST)
    public String addKeluargaSubmit(Model model, @ModelAttribute KeluargaModel keluarga)
    {
    	keluarga.generateNkk(keluargaDAO);
    	keluargaDAO.addKeluarga(keluarga);
        model.addAttribute("nomor_kk", keluarga.getNomor_kk());
        return "successAdd";
        
    }
    
    @RequestMapping("/penduduk/ubah/{nik}")
	public String ubahPenduduk(Model model, @PathVariable(value = "nik") String nik) {
		PendudukModel penduduk = pendudukDAO.selectPenduduk(nik);
		model.addAttribute("penduduk", penduduk);
		return "updatePenduduk";
	}
    
    @RequestMapping(value = "/penduduk/ubah/{nik}", method = RequestMethod.POST)
	public String updatePenduduk(Model model, @PathVariable(value = "nik") String nik,
			@ModelAttribute PendudukModel penduduk) {
		PendudukModel pendudukLama = pendudukDAO.selectPenduduk(nik);

		penduduk.setId(pendudukLama.getId());
		pendudukDAO.updatePenduduk(penduduk);
		penduduk.setNik(nik);

		if (!pendudukLama.getTanggal_lahir().equals(penduduk.getTanggal_lahir())) {
			pendudukLama.generateNik(keluargaDAO, pendudukDAO);
		}

		model.addAttribute("nik", pendudukLama.getNik());
		return "updateSuccess";
	}
    
    @RequestMapping("/penduduk/wafat")
    public String pendudukMati(Model model, @RequestParam(value = "nik", required = false) String nik) {
		PendudukModel penduduk = pendudukDAO.selectPenduduk(nik);
		pendudukDAO.setWafat(penduduk);
		return "suksesNonaktif";
	}
    
    @RequestMapping("/penduduk/cari")
    public String cariPenduduk (Model model)
    {
     return "cari";
    }
    
	@RequestMapping("/keluarga/ubah/{nomor_kk}")
	public String ubahKeluarga(Model model, @PathVariable(value = "nomor_kk") String nomor_kk) {
		KeluargaModel keluarga = keluargaDAO.selectKeluarga(nomor_kk);
		model.addAttribute("kelurahans", alamatDAO.selectKelurahans());
		model.addAttribute("keluarga", keluarga);
		return "updateKeluarga";
	}

	@RequestMapping(value = "/keluarga/ubah/{nomor_kk}", method = RequestMethod.POST)
	public String ubahKeluarga(Model model, @PathVariable(value = "nomor_kk") String nomor_kk,
			@ModelAttribute KeluargaModel keluarga) {
		KeluargaModel keluargaLama = keluargaDAO.selectKeluarga(nomor_kk);
		keluarga.setNomor_kk(keluargaLama.getNomor_kk());
		keluarga.setId_keluarga(keluargaLama.getId_keluarga());
		keluarga.setIs_tidak_berlaku(keluargaLama.getIs_tidak_berlaku());

		if (keluargaLama.getId_kelurahan() != keluarga.getId_kelurahan()) {
			keluarga.generateNkk(keluargaDAO);
		}
		
		keluargaDAO.updateKeluarga(keluarga);

		return "updateSuccess";
	}
}
