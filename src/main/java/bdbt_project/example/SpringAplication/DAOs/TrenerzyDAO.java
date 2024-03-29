package bdbt_project.example.SpringAplication.DAOs;

import bdbt_project.example.SpringAplication.data_representation.Trener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;
import java.util.List;


@Repository
public class TrenerzyDAO {
    @Autowired
    /* Import org.springframework.jd....Template */
    private JdbcTemplate jdbcTemplate;

    public TrenerzyDAO(JdbcTemplate jdbcTemplate){
        super();
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Trener> listUser(){
        String sql = "select p.nr_pracownika, p.Imie, p.Nazwisko, p.Telefon, p.Email,p.Nr_klubu, t.Dyscyplina, t.Stopien_zaawansowania from Pracownicy p join Trenerzy t on p.Nr_pracownika = t.Nr_pracownika";
        List<Trener> trenerzyList = jdbcTemplate.query(sql, BeanPropertyRowMapper.newInstance(Trener.class));
        return trenerzyList;
    }

    public List<Trener> list(){
        String sql = "select * from Trenerzy ";
        List<Trener> trenerzyList = jdbcTemplate.query(sql, BeanPropertyRowMapper.newInstance(Trener.class));
        return trenerzyList;
    }


    /* Insert – wstawianie nowego wiersza do bazy */
    public void save(Trener trener) {
        SimpleJdbcInsert insertAction = new SimpleJdbcInsert(jdbcTemplate);
        insertAction.withTableName("trenerzy").usingColumns("stopien_zaawansowania", "nr_licencji", "data_waznosci_licencji", "dyscyplina", "czy_szkolenie_rko", "nr_pracownika");
        BeanPropertySqlParameterSource param = new BeanPropertySqlParameterSource(trener);
        insertAction.execute(param);
    }


    /* Read – odczytywanie danych z bazy */
    public Trener get(int id) {
        return null;
    }


    /* Update – aktualizacja danych */
    public void update(Trener trener) {
        String sql = "UPDATE Trenerzy SET stopien_zaawansowania=:stopienZaawansowania, nr_licencji=:nrLicencji, data_waznosci_licencji=:dataWaznosciLicencji, " +
                "dyscyplina=:dyscyplina, czy_szkolenie_rko=:czySzkolenieRko WHERE nr_pracownika=:nrPracownika";
        BeanPropertySqlParameterSource param = new BeanPropertySqlParameterSource(trener);
        NamedParameterJdbcTemplate template = new NamedParameterJdbcTemplate(jdbcTemplate);
        template.update(sql, param);
    }


    /* Delete – wybrany rekord z danym id */
    public void delete(int id) {
        String sql = "DELETE FROM Trenerzy WHERE nr_pracownika = ?";
        jdbcTemplate.update(sql,id);

    }
    public void delete1(int id){
        String sql = "DELETE FROM Pracownicy WHERE nr_pracownika = ?";
        jdbcTemplate.update(sql,id);
    }
}
