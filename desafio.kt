// [Template no Kotlin Playground](https://pl.kotl.in/WcteahpyN)

enum class Nivel { BASICO, INTERMEDIARIO, DIFICIL }

fun tratarPalavraNivel(nivel: Nivel):String{
    when(nivel){
        Nivel.DIFICIL -> return "Dificil"
        Nivel.INTERMEDIARIO -> return "Intermediário"
        Nivel.BASICO -> return "Básico"
        else -> {
            return "Sem Nível"
        }
    }
}


class Usuario(val nome: String, val idade: Int, val ID: Int, val conteudosAprendidos: MutableList<ConteudoEducacional> = mutableListOf()) {
    fun aprenderConteudo(conteudo: ConteudoEducacional) {
        println("O aluno $nome aprendeu ${conteudo.nome}.")
        conteudosAprendidos.add(conteudo)
    }
   	override fun toString(): String {
        return 	"Nome = $nome" +
        		"\nIdade = $idade" +
        		"\nMatricula = $ID " +
        		"\nConteúdos =\n$conteudosAprendidos"
    }
    
    
}


data class ConteudoEducacional(val nome: String, val duracao: Int = 60, val nivel: Nivel){
    override fun toString(): String {
        return 	"Nome = $nome" +
        		"\nDuração = $duracao" +
        		"\nNivel = $nivel \n"
    }
}

data class Formacao(val nome: String, var conteudos: MutableList<ConteudoEducacional>) {

    val inscritos = mutableListOf<Usuario>()
    
    fun matricular(usuario: Usuario) {
        if(usuario in inscritos){
            println("O aluno ${usuario.nome} já está inscrito")
        }
        else{
            println("O aluno ${usuario.nome} foi matriculado na formação $nome.")
        	inscritos.add(usuario)
        }
    }
    
    fun alunosInscritos() {
        println("Os alunos matriculados são: ")
        for (aluno in inscritos) {
            println("${aluno.nome} com idade de ${aluno.idade}")
        }
    }
    
    fun listaConteudos(){
        println("Os conteúdos programáticos de ${this.nome} são: ")
        for (i in 0 until conteudos.size) {
            println("${i+1} : ${conteudos[i].nome} com duração de ${conteudos[i].duracao} horas de nível : ${tratarPalavraNivel(conteudos[i].nivel)}")
        }
    }
    
    fun adicionarConteudo(novoConteudo: ConteudoEducacional){
        println("O conteúdo ${novoConteudo.nome} foi adicionada aos conteudos da formação $nome.")
        conteudos.add(novoConteudo)
    }
    
    fun removerConteudo(conteudo: ConteudoEducacional){
        println("O conteúdo ${conteudo.nome} foi removido aos conteudos da formação $nome.")
        conteudos.remove(conteudo)
    }
    
    fun formou(aluno: Usuario){
        println("Parabéns ${aluno.nome}! Você aprendeu tudo sobre o curso ${this.nome} \nOs conteúdos foram adicionado aos seu currículo.")
        for(conteudo in conteudos){
            if(conteudo !in aluno.conteudosAprendidos){
               aluno.conteudosAprendidos.add(conteudo) 
            } 
        }
    }
    
    
}

fun main() {
    val hugo = Usuario(nome = "Hugo", idade = 26, 1)
    val conteudo1 = ConteudoEducacional("Conhecendo Kotlin", 2, Nivel.BASICO)
    val conteudo2 = ConteudoEducacional("Introdução à Kotlin", 2, Nivel.BASICO)
    val conteudo3 = ConteudoEducacional("Controle de Fluxo", 1, Nivel.INTERMEDIARIO)
    val bootcamp = Formacao("Kotlin", mutableListOf(conteudo1, conteudo2, conteudo3))
    val julia = Usuario(nome = "Júlia", idade = 16, 2)
    val conteudo4 = ConteudoEducacional("Git", 1, Nivel.BASICO)
    
    
    bootcamp.matricular(hugo)
    bootcamp.matricular(julia)
    bootcamp.alunosInscritos()
    bootcamp.listaConteudos()
    bootcamp.adicionarConteudo(conteudo4)
    bootcamp.listaConteudos()
    julia.aprenderConteudo(conteudo4)
    bootcamp.matricular(hugo)
    bootcamp.formou(julia)
    println(hugo)
    println(julia)
    bootcamp.removerConteudo(conteudo1)
    bootcamp.listaConteudos()
    
}
