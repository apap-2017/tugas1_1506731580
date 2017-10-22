package com.example.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Many;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Update;


import com.example.model.PendudukModel;
import com.example.model.KeluargaModel;
import java.util.List;

@Mapper
public interface KeluargaMapper {
	@Select("SELECT * FROM penduduk JOIN keluarga "
			+ " ON penduduk.id_keluarga = keluarga.id WHERE nomor_kk = #{nomor_kk}")
	List<PendudukModel> selectWarga(@Param("nomor_kk") String nomor_kk);

	@Select("SELECT * FROM keluarga JOIN kelurahan JOIN kecamatan JOIN kota "
			+ "ON keluarga.id_kelurahan = kelurahan.id " + "AND kelurahan.id_kecamatan = kecamatan.id "
			+ "AND kecamatan.id_kota = kota.id " + "WHERE nomor_kk = #{nomor_kk}")
	@Results(value = { @Result(property = "id_keluarga", column = "id"),
			@Result(property = "nomor_kk", column = "nomor_kk"), @Result(property = "alamat", column = "alamat"),
			@Result(property = "RT", column = "RT"), @Result(property = "RW", column = "RW"),
			@Result(property = "nama_kelurahan", column = "nama_kelurahan"),
			@Result(property = "nama_kecamatan", column = "nama_kecamatan"),
			@Result(property = "kode_kecamatan", column = "kode_kecamatan"),
			@Result(property = "id_kelurahan", column = "id_kelurahan"),
			@Result(property = "is_tidak_berlaku", column = "is_tidak_berlaku"),
			@Result(property = "warga", column = "nomor_kk", javaType = List.class, many = @Many(select = "selectWarga")) })
	KeluargaModel selectKeluarga(@Param("nomor_kk") String nomor_kk);

	@Select("SELECT * FROM keluarga JOIN kelurahan JOIN kecamatan JOIN kota"
			+ " ON keluarga.id_kelurahan = kelurahan.id AND " + "kelurahan.id_kecamatan = kecamatan.id "
			+ " AND kecamatan.id_kota = kota.id" + " WHERE keluarga.id = #{id}")
	@Results(value = { @Result(property = "nomor_kk", column = "nomor_kk"),
			@Result(property = "alamat", column = "alamat"), @Result(property = "RT", column = "RT"),
			@Result(property = "RW", column = "RW"), @Result(property = "nama_kelurahan", column = "nama_kelurahan"),
			@Result(property = "nama_kecamatan", column = "nama_kecamatan"),
			@Result(property = "kode_kecamatan", column = "kode_kecamatan"),
			@Result(property = "nama_kota", column = "nama_kota"),
			@Result(property = "warga", column = "nomor_kk", javaType = List.class, many = @Many(select = "selectWarga")) })
	KeluargaModel getKeluarga(@Param("id") String id);

	@Insert("INSERT INTO keluarga (alamat, nomor_kk, rt, rw, id_kelurahan)"
			+ " VALUES ('${alamat}', '${nomor_kk}', '${RT}', '${RW}', '${id_kelurahan}')")
	void addKeluarga(KeluargaModel keluarga);
	
	@Update("UPDATE keluarga SET nomor_kk = '${nomor_kk}', alamat = "
			+ "'${alamat}', RT = '${RT}', RW = '${RW}', id_kelurahan = "
			+ "'${id_kelurahan}', is_tidak_berlaku = 0 WHERE id=${id}")
	void updateKeluarga(KeluargaModel keluarga);
}
