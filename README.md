# Anillo_de_comunicaciones
Pequeño projecto que hice en 2º de DAM en el cual usaba sockets y threads para pasar datos de un servidor a otro en forma de anillo donde el último devolvia el mensaje al primero.

Un primer hilo servidor envía una cadena de texto a un segundo servidor, el segunda envía la misma cadena al tercero, el tercero al cuarto, el cuarto al quinto y el quinto lo envía de vuelta al primero.
