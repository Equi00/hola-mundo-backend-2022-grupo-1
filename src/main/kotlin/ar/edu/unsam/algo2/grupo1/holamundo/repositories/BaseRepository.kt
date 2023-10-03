package ar.edu.unsam.algo2.grupo1.repositories

import ar.edu.unsam.algo2.grupo1.exceptions.ElementNotFoundException

abstract class BaseRepository<T : BaseData> {
    protected var elements: MutableSet<T> = mutableSetOf<T>()
    protected var autoId: Int = 0

    fun getAll(): List<T> = elements.toList()

    fun create(t: T): Int {
        autoId++.also { t.id = it }
        elements.add(t)
        return t.id
    }

    fun createMany(t: List<T>) {
        t.forEach { this.create(it) }
    }

    fun delete(t: T) {
        if (this.existsId(t.id))
            elements.remove(t)
        else throw ElementNotFoundException("Object ID")
    }

    fun deleteById(id: Int): T {
        val elementToErase = getById(id)
        if (elementToErase != null)
            elements.remove(elementToErase)
        else throw ElementNotFoundException("Object ID")
        return elementToErase
    }

    fun update(t: T): T {
        if (this.existsId(t.id)) {
            elements.remove(getById(t.id))
            elements.add(t)
            return t
        } else throw ElementNotFoundException("Object ID")
    }

    fun getById(id: Int) =
        elements.find { it.id == id }

    abstract fun search(value: String): List<T>
    open fun setDefaultValues() {}

    private fun existsId(id: Int): Boolean = elements.any { it.id == id }

    fun elementsSize(): Int = elements.size

    fun clear(){
        this.elements = mutableSetOf()
    }
}