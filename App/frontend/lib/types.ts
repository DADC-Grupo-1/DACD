export interface Ingredient {
  name: string
  amount: string
  price: number
}

export interface Recipe {
  id: string
  name: string
  description: string
  image: string
  cookTime: number
  servings: number
  ingredients: Ingredient[]
  instructions: string[]
  dietary: {
    vegan: boolean
    vegetarian: boolean
    dairyFree: boolean
    glutenFree: boolean
  }
}
