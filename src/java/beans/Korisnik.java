
package beans;

public class Korisnik {
    private int id;
    private String imeprezime="";
    private String username="";
    private String password="";
    private String email="";
    private String telefon="";
    private String adresa="";
    private String power="";
    
    public int getId()
    {
        return id;
    }
    public void setId(int id)
    {
        this.id = id;
    }
    public String getImeprezime()
    {
        return imeprezime;
    }
    public void setImeprezime(String imeprezime)
    {
        this.imeprezime = imeprezime;
    }
    public String getUsername()
    {
        return username;
    }
    public void setUsername(String username)
    {
        this.username = username;
    }
    public String getPassword()
    {
        return password;
    }
    public void setPassword(String password)
    {
        this.password = password;
    }
    public String getEmail()
    {
       return email;
    }
    public void setEmail(String email)
    {
        this.email = email;
    }
    public String getTelefon()
    {
        return telefon;
    }
    public void setTelefon(String telefon)
    {
        this.telefon = telefon;
    }
    public String getAdresa()
    {
        return adresa;
    }
    public void setAdresa(String adresa)
    {
        this.adresa = adresa;
    }
     public String getPower()
    {
        return power;
    }
    public void setPower(String power)
    {
        this.power = power;
    }
}
