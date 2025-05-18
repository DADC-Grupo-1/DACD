import { type ClassValue, clsx } from "clsx"
import { twMerge } from "tailwind-merge"
import { recipes } from "./data"

export function cn(...inputs: ClassValue[]) {
  return twMerge(clsx(inputs))
}

export function formatCurrency(amount: number): string {
  return new Intl.NumberFormat("en-US", {
    style: "currency",
    currency: "USD",
    minimumFractionDigits: 2,
  }).format(amount)
}

export function getAllIngredients(): string[] {
  // Extract all unique ingredient names from all recipes
  const ingredientSet = new Set<string>()

  recipes.forEach((recipe) => {
    recipe.ingredients.forEach((ingredient) => {
      ingredientSet.add(ingredient.name)
    })
  })

  return Array.from(ingredientSet).sort()
}
