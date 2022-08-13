package com.example.podcastonthego.controllers


import com.example.podcastonthego.dto.NewPodcastRequest
import com.example.podcastonthego.models.Podcast
import com.example.podcastonthego.services.PodcastService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.ui.ModelMap
import org.springframework.web.bind.annotation.*
import org.springframework.web.servlet.ModelAndView


@Controller
class HTMLController {

    @Autowired
    lateinit var podcastService: PodcastService
    @GetMapping
    fun index(): String {
        return "Check!"
    }



    @GetMapping("/test")
    fun listStudents(model: Model): String {
        model.addAttribute("test", "this is coming from the controller")
        return "index"
    }

    /*
    @GetMapping("/podcastsTest")
    fun getAllPodcast(model: Model): String {
        val AllPodcasts =  listOf(
            Podcast(1,"TalkByNight", "Andy","img","pc"),
            Podcast(2,"TalkByMorning", "Andy","img","pc"),
            Podcast(3,"TalkByEvening", "Andy","img","pc")
        )
        model.addAttribute("allPodcastTest", AllPodcasts)
        return "index"
    }
*/

    @GetMapping("/podcastsTest")
    fun getAllPodcast(model: Model): String {

        val allPodcasts = podcastService.getAllPodcast()
        println(allPodcasts)
        model.addAttribute("allPodcastTest", allPodcasts)
        return "index"
    }

    @GetMapping("podcasts")
    fun getAllPodcasts(): MutableList<Podcast> {
        return podcastService.getAllPodcast()
    }




    @GetMapping("2names/{param1}")
    fun names(@PathVariable param1: String, model: Model): String {
        val hello = "how are you $param1"
        model.addAttribute("littleSentence", hello)
        return "index"
    }

    @PostMapping("/student")
    fun addStudent(newPodcastRequest: NewPodcastRequest, model: Model): String {
        

        podcastService.saveNewPodcast(newPodcastRequest)

        return "addPodcast"
    }

    @GetMapping("/student")
    fun createStudentPage(model: Model): String {
        model.addAttribute("NewPodcastRequest", NewPodcastRequest())
        return "addPodcast"
    }

    /*
    @RequestMapping("tt")
    fun newPodcast(@ModelAttribute newPodcastRequest: NewPodcastRequest,model: Model ): String{
model.addAttribute("addPodcastTest", podcastService.saveNewPodcast(newPodcastRequest))
        return "addPodcast"
    }

    @PostMapping("/test2")
    fun addDevice( @ModelAttribute newPodcastRequest: NewPodcastRequest, model: ModelMap): ModelAndView {
        podcastService.saveNewPodcast(newPodcastRequest)
        return ModelAndView("index", model)
    }

    @GetMapping("/add")
    fun addNewStudent(model: Model): String? {
        model.addAttribute(
            "newPodcastRequest",
            newPodcastRequest()
        ) //create a new bean so that your form can bind the input fields to it
        return "add" //let's say add.html this is the name of your form
    }

    @PostMapping("/add")
    fun addNewStudent(@ModelAttribute newPodcastRequest: NewPodcastRequest?, model: Model?): String? {

        //call any service methods to do any processing here
        podcastService.saveNewPodcast(newPodcastRequest)
        return "redirect:/allstudents" //this would be your confirmation page
    }
    /*
        @GetMapping("/test1")
        fun getPodcastForm(model: Model): String {

            model.addAttribute("newPodcastRequest", podcastForm())
            return "index"
        }

        @RequestMapping(value = ["/test1"], method = [RequestMethod.POST])
        fun addPodcastForm(@ModelAttribute newPodcastRequest: NewPodcastRequest, bindingResult: BindingResult, model: Model): String? {
            if (bindingResult.hasErrors()) {
                //errors processing
            }
            model.addAttribute("studentForm", podcastService.saveNewPodcast(newPodcastRequest))
            return "result"
        }
    */
*/
}