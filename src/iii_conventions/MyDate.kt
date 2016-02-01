package iii_conventions

data class MyDate(val year: Int, val month: Int, val dayOfMonth: Int) : Comparable<MyDate> {
    override fun compareTo(other: MyDate): Int {
        return if (this == other) 0
        else if (year < other.year)  -1
        else if (month < other.month) -1
        else if (dayOfMonth < other.dayOfMonth) -1
        else 1
    }
}

operator fun MyDate.rangeTo(other: MyDate): DateRange = DateRange(this, other)

enum class TimeInterval {
    DAY,
    WEEK,
    YEAR
}

class DateRange(override val start: MyDate, override val endInclusive: MyDate) : ClosedRange<MyDate> {
    operator fun iterator(): Iterator<MyDate> {
        return object : Iterator<MyDate> {
            var current = start
            override fun hasNext(): Boolean {
                return current <= endInclusive
            }

            override fun next(): MyDate {
                val nextElement = current
                current = current.nextDay()
                return nextElement
            }
        }
    }
}
