
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class PagesController {

    @GetMapping("/")
    public String home() {
        return "home";
    }

    @GetMapping("/pagina-clientes")
    public String clientes(Model model) {
        // Se quiser mostrar dados reais dos clientes:
        // model.addAttribute("clientes", clienteRepository.findAll());
        return "clientes";
    }

    @GetMapping("/veiculos-web")
    public String veiculos(Model model) {
        // model.addAttribute("veiculos", veiculoRepository.findAll());
        return "veiculos";
    }

    @GetMapping("/aluguel")
    public String aluguel(Model model) {
        // model.addAttribute("aluguels", aluguelRepository.findAll());
        return "aluguel";
    }
}
