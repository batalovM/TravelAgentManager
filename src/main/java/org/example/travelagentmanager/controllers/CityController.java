package org.example.travelagentmanager.controllers;

import org.example.travelagentmanager.model.City;
import org.example.travelagentmanager.service.CityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author batal
 * @Date 10.10.2024
 */
@Controller
@RequestMapping("/cities")
public class CityController {
    private final CityService cityService;
    @Autowired
    public CityController(CityService cityService) {
        this.cityService = cityService;
    }
    // Получение списка всех городов и передача их в шаблон
    @GetMapping
    public String getAllCities(Model model) {
        List<City> cities = cityService.getAllCities();
        model.addAttribute("cities", cities);
        return "cities/list"; // возвращаем шаблон list.html
    }

    // Показать форму для добавления нового города
    @GetMapping("/new")
    public String showCreateForm(Model model) {
        model.addAttribute("city", new City());
        return "cities/create"; // возвращаем шаблон create.html
    }

    // Обработать форму добавления нового города
    @PostMapping
    public String addCity(@ModelAttribute("city") City city) {
        cityService.addCity(city);
        return "redirect:/cities"; // перенаправляем на список городов
    }

    // Показать форму для редактирования существующего города
    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable int id, Model model) {
        City city = cityService.getCityById(id);
        model.addAttribute("city", city);
        return "cities/edit"; // возвращаем шаблон edit.html
    }

    // Обработать форму редактирования города
    @PostMapping("/update/{id}")
    public String updateCity(@PathVariable int id, @ModelAttribute("city") City city) {
        city.setId(id);
        cityService.updateCity(city);
        return "redirect:/cities";
    }

    // Удаление города
    @GetMapping("/delete/{id}")
    public String deleteCity(@PathVariable int id) {
        cityService.deleteCity(id);
        return "redirect:/cities";
    }
}
