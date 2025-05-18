"use client"

import { useState } from "react"
import { Badge } from "@/components/ui/badge"
import { cn } from "@/lib/utils"

export type DietaryFilter = "vegan" | "vegetarian" | "dairyFree" | "glutenFree" | null

interface DietaryFilterProps {
  onFilterChange: (filters: DietaryFilter[]) => void
}

export default function DietaryFilter({ onFilterChange }: DietaryFilterProps) {
  const [activeFilters, setActiveFilters] = useState<DietaryFilter[]>([])

  const toggleFilter = (filter: DietaryFilter) => {
    setActiveFilters((current) => {
      const newFilters = current.includes(filter) ? current.filter((f) => f !== filter) : [...current, filter]

      onFilterChange(newFilters)
      return newFilters
    })
  }

  const dietaryOptions: { value: DietaryFilter; label: string }[] = [
    { value: "vegan", label: "Vegan" },
    { value: "vegetarian", label: "Vegetarian" },
    { value: "dairyFree", label: "Dairy Free" },
    { value: "glutenFree", label: "Gluten Free" },
  ]

  return (
    <div className="flex flex-wrap gap-2 mb-6">
      <span className="text-sm font-medium text-muted-foreground mr-2 self-center">Dietary:</span>
      {dietaryOptions.map((option) => (
        <Badge
          key={option.value}
          variant={activeFilters.includes(option.value) ? "default" : "outline"}
          className={cn(
            "cursor-pointer hover:bg-primary/90",
            activeFilters.includes(option.value)
              ? "bg-primary text-primary-foreground"
              : "bg-background hover:text-primary-foreground",
          )}
          onClick={() => toggleFilter(option.value)}
        >
          {option.label}
        </Badge>
      ))}
    </div>
  )
}
