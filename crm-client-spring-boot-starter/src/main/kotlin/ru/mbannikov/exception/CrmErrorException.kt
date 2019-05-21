package ru.mbannikov.exception

import java.lang.RuntimeException

class CrmErrorException(message: String) : RuntimeException(message)