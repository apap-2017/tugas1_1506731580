package com.example.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Update;

import com.example.model.PendudukModel;

@Mapper
public interface PendudukMapper
{
	@Select("select id, nik, nama, tanggal_lahir, tempat_lahir, jenis_kelamin, is_wni, agama, status_perkawinan"
			+ ", status_dalam_keluarga, golongan_darah, is_wafat, id_keluarga, pekerjaan from penduduk where nik = #{nik}")
    @Results(value = {
    		@Result(property="id", column="id"),
    		@Result(property="nik", column="nik"),
    		@Result(property="nama", column="nama"),
    		@Result(property="tempat_lahir", column="tempat_lahir"),
    		@Result(property="tanggal_lahir", column="tanggal_lahir"),
    		@Result(property="jenis_kelamin", column="jenis_kelamin"),
    		@Result(property="is_wni", column="is_wni"),
    		@Result(property="pekerjaan", column="pekerjaan"),
    		@Result(property="agama", column="agama"),
    		@Result(property="status_perkawinan", column="status_perkawinan"),
    		@Result(property="status_dalam_keluarga", column="status_dalam_keluarga"),
    		@Result(property="golongan_darah", column="golongan_darah"),
    		@Result(property="id_keluarga", column="id_keluarga"),   		
      		@Result(property="is_wafat", column="is_wafat")
    })
    PendudukModel selectPenduduk (@Param("nik") String nik);
	
	@Insert("INSERT INTO penduduk (nik, nama, tempat_lahir, tanggal_lahir, jenis_kelamin, is_wni,"
			+ " id_keluarga, agama, pekerjaan, status_perkawinan, "
			+ " golongan_darah, is_wafat)"
    		+ " VALUES ('${nik}', '${nama}', '${tempat_lahir}', '${tanggal_lahir}', '${jenis_kelamin}',"
    		+ " ${is_wni}, '${id_keluarga}', '${agama}', '${pekerjaan}', '${status_perkawinan}', "
    		+ " '${golongan_darah}',"
    		+ " '${is_wafat}')")
    void addPenduduk (PendudukModel penduduk);
	
	@Update("UPDATE penduduk SET nik = #{nik}, nama = #{nama}, tempat_lahir = #{tempat_lahir}, tanggal_lahir = #{tanggal_lahir}, "
			+ "jenis_kelamin = #{jenis_kelamin}, is_wni = #{is_wni}, golongan_darah = #{golongan_darah}, is_wafat = #{is_wafat} "
			+ "WHERE id = #{id}")
	void updatePenduduk(PendudukModel penduduk);
	
	@Update("UPDATE penduduk SET is_wafat = 1 WHERE nik = #{nik}")
	void setWafat(@Param("nik") String nik);
}
