package nightmarethreatreis.com.github.mvp.managers;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javafx.scene.layout.Pane;
import nightmarethreatreis.com.github.mvp.model.Admin;
import nightmarethreatreis.com.github.mvp.model.Korisnik;
import nightmarethreatreis.com.github.mvp.model.Kupac;
import nightmarethreatreis.com.github.mvp.model.Radnik;
import nightmarethreatreis.com.github.mvp.screens.externalComponents.NavbarLink;

@Component
public class NavbarManager {
	@Autowired
	private ScreenManager screenManager;
	@Autowired
	private SessionManager sessionManager;
	
	private Map<Class<? extends Korisnik>, List<NavbarLink>> links = new HashMap<>();
	
	private NavbarLink createLink(String name, String screenName) {
		return new NavbarLink(name, event -> screenManager.activate(screenName));
	}
	
	private NavbarLink createLogoutLink(String name) {
		return new NavbarLink(name, event -> {
			sessionManager.logout();
			screenManager.activate("login");
		});
	}
	
	{
		reloadNavbar();
	}
	
	
	
	public void reloadNavbar() {
		List<NavbarLink> kupacLinks = new LinkedList<>();
		List<NavbarLink> adminLinks = new LinkedList<>();
		List<NavbarLink> radnikLinks = new LinkedList<>();
	
		// KUPAC LINKS
		kupacLinks.add(createLink("Početna", "home"));
		kupacLinks.add(createLink("Promena korisničkog imena", "changeUsername"));
		kupacLinks.add(createLink("Promena lozinke", "changePassword"));
		kupacLinks.add(createLogoutLink("Odjavi se"));
		
		// ADMIN LINKS
		adminLinks.add(createLink("Početna", "home"));
		adminLinks.add(createLink("Pregled dramskih radnika", "showAllPersonel"));
		adminLinks.add(createLink("Promena korisničkog imena", "changeUsername"));
		adminLinks.add(createLink("Promena lozinke", "changePassword"));
		adminLinks.add(createLogoutLink("Odjavi se"));
		
		// RADNIK LINKS
		radnikLinks.add(createLink("Početna", "home"));
		radnikLinks.add(createLink("Promena korisničkog imena", "changeUsername"));
		radnikLinks.add(createLink("Promena lozinke", "changePassword"));
		radnikLinks.add(createLogoutLink("Odjavi se"));
		
		links.put(Kupac.class, kupacLinks);
		links.put(Admin.class, adminLinks);
		links.put(Radnik.class, radnikLinks);
	}
			
	private List<NavbarLink> getNavbarLinkList(Class<? extends Korisnik> uloga) {
		return links.get(uloga);
	}
	
	public List<NavbarLink> getNavbarLinks() {
		Korisnik korisnik = sessionManager.getLoggedInKorisnik();
		if(korisnik == null) {
			List<NavbarLink> lista = new LinkedList<>();
			lista.add(createLink("Login", "login"));
			return lista;
		}
		return getNavbarLinkList(korisnik.getClass());
	}
	
	public void updateNavbar(Pane navbar) {
		if(navbar == null) {
			throw new NullPointerException("Navbar ne sme biti null");
		}
		navbar.getChildren().clear();
		navbar.getChildren().addAll(getNavbarLinks());
	}
}
