package bozhko_project.electronic_board.swagger;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
public class HomeController {
	@RequestMapping("/")
	public String index(Model model) {
		return "login";
//		return "redirect:swagger-ui.html";
	}

}