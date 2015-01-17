package com.github.t3hnar.scalax

import scala.language.experimental.macros

/**
 * @author Yaroslav Klymko
 */
@deprecated("Getting read of scala-reflect dependency", "v2.3, 2015-01-17")
trait FieldsMap

@deprecated("Getting read of scala-reflect dependency", "v2.3, 2015-01-17")
object FieldsMap {
  implicit class Mappable[M <: FieldsMap](val model: M) extends AnyVal {
    def fieldsMap: Map[String, Any] = macro Macros.fieldsMap_impl[M]
  }

  private object Macros {
    import scala.reflect.macros.Context

    def fieldsMap_impl[T: c.WeakTypeTag](c: Context) = {
      import c.universe._

      val mapApply = Select(reify(Map).tree, newTermName("apply"))
      val model = Select(c.prefix.tree, newTermName("model"))

      val pairs = weakTypeOf[T].declarations.collect {
        case m: MethodSymbol if m.isCaseAccessor =>
          val name = c.literal(m.name.decoded)
          val value = c.Expr(Select(model, m.name))
          reify(name.splice -> value.splice).tree
      }

      c.Expr[Map[String, Any]](Apply(mapApply, pairs.toList))
    }
  }
}