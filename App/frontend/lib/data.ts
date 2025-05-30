import type { Recipe } from "./types"

export const recipes: Recipe[] = [
  {
    id: "spaghetti-bolognese",
    name: "Spaghetti Bolognese",
    description: "A classic Italian pasta dish with a rich meaty sauce. Perfect for a family dinner.",
    image: "/placeholder.svg?height=400&width=600",
    cookTime: 45,
    servings: 4,
    ingredients: [
      { name: "spaghetti", amount: "400g", price: 1.2 },
      { name: "ground beef", amount: "500g", price: 4.5 },
      { name: "onion", amount: "1 large", price: 0.4 },
      { name: "garlic", amount: "2 cloves", price: 0.3 },
      { name: "canned tomatoes", amount: "400g", price: 0.8 },
      { name: "tomato paste", amount: "2 tbsp", price: 0.4 },
      { name: "carrots", amount: "2 medium", price: 0.6 },
      { name: "celery", amount: "2 stalks", price: 0.7 },
      { name: "red wine", amount: "100ml", price: 1.5 },
      { name: "olive oil", amount: "2 tbsp", price: 0.5 },
      { name: "dried oregano", amount: "1 tsp", price: 0.2 },
      { name: "salt and pepper", amount: "to taste", price: 0.1 },
    ],
    instructions: [
      "Heat olive oil in a large pan over medium heat.",
      "Add chopped onions, carrots, and celery. Cook until softened, about 5 minutes.",
      "Add minced garlic and cook for another minute.",
      "Add ground beef and cook until browned, breaking it up with a spoon.",
      "Pour in red wine and let it simmer until reduced by half.",
      "Add canned tomatoes, tomato paste, oregano, salt, and pepper.",
      "Simmer on low heat for 30 minutes, stirring occasionally.",
      "Meanwhile, cook spaghetti according to package instructions.",
      "Drain pasta and serve topped with the bolognese sauce.",
    ],
    dietary: {
      vegan: false,
      vegetarian: false,
      dairyFree: true,
      glutenFree: false,
    },
  },
  {
    id: "chicken-stir-fry",
    name: "Chicken Stir Fry",
    description: "A quick and healthy stir fry with chicken and colorful vegetables in a savory sauce.",
    image: "/placeholder.svg?height=400&width=600",
    cookTime: 25,
    servings: 3,
    ingredients: [
      { name: "chicken breast", amount: "450g", price: 3.8 },
      { name: "bell peppers", amount: "2", price: 1.6 },
      { name: "broccoli", amount: "1 head", price: 1.2 },
      { name: "carrots", amount: "2", price: 0.6 },
      { name: "soy sauce", amount: "3 tbsp", price: 0.4 },
      { name: "honey", amount: "1 tbsp", price: 0.3 },
      { name: "garlic", amount: "3 cloves", price: 0.4 },
      { name: "ginger", amount: "1 inch", price: 0.3 },
      { name: "vegetable oil", amount: "2 tbsp", price: 0.3 },
      { name: "rice", amount: "1 cup", price: 0.9 },
    ],
    instructions: [
      "Slice chicken breast into thin strips.",
      "Chop all vegetables into bite-sized pieces.",
      "Mix soy sauce, honey, minced garlic, and grated ginger in a small bowl.",
      "Heat oil in a wok or large frying pan over high heat.",
      "Add chicken and stir-fry until no longer pink, about 4-5 minutes.",
      "Remove chicken and set aside.",
      "Add vegetables to the pan and stir-fry for 3-4 minutes until crisp-tender.",
      "Return chicken to the pan and add the sauce.",
      "Stir-fry for another 2 minutes until everything is well coated and heated through.",
      "Serve hot over cooked rice.",
    ],
    dietary: {
      vegan: false,
      vegetarian: false,
      dairyFree: true,
      glutenFree: true,
    },
  },
  {
    id: "vegetable-curry",
    name: "Vegetable Curry",
    description: "A flavorful and aromatic vegetable curry that's both healthy and satisfying.",
    image: "/placeholder.svg?height=400&width=600",
    cookTime: 35,
    servings: 4,
    ingredients: [
      { name: "potatoes", amount: "2 large", price: 0.8 },
      { name: "cauliflower", amount: "1 small head", price: 1.5 },
      { name: "chickpeas", amount: "1 can", price: 0.9 },
      { name: "spinach", amount: "200g", price: 1.2 },
      { name: "onion", amount: "1 large", price: 0.4 },
      { name: "garlic", amount: "3 cloves", price: 0.4 },
      { name: "ginger", amount: "1 inch", price: 0.3 },
      { name: "curry powder", amount: "2 tbsp", price: 0.6 },
      { name: "coconut milk", amount: "400ml", price: 1.8 },
      { name: "vegetable broth", amount: "200ml", price: 0.5 },
      { name: "tomatoes", amount: "2 medium", price: 0.7 },
      { name: "vegetable oil", amount: "2 tbsp", price: 0.3 },
      { name: "rice", amount: "1 cup", price: 0.9 },
    ],
    instructions: [
      "Heat oil in a large pot over medium heat.",
      "Add diced onion and cook until translucent, about 3-4 minutes.",
      "Add minced garlic and ginger, cook for another minute.",
      "Add curry powder and stir for 30 seconds until fragrant.",
      "Add diced potatoes, cauliflower florets, and diced tomatoes. Stir to coat with spices.",
      "Pour in coconut milk and vegetable broth. Bring to a simmer.",
      "Cover and cook for 15-20 minutes until vegetables are tender.",
      "Add drained chickpeas and spinach. Cook for another 5 minutes.",
      "Season with salt to taste.",
      "Serve hot with rice.",
    ],
    dietary: {
      vegan: true,
      vegetarian: true,
      dairyFree: true,
      glutenFree: true,
    },
  },
  {
    id: "avocado-toast",
    name: "Avocado Toast",
    description: "A simple, nutritious breakfast that's quick to prepare and full of healthy fats.",
    image: "/placeholder.svg?height=400&width=600",
    cookTime: 10,
    servings: 2,
    ingredients: [
      { name: "avocado", amount: "1 ripe", price: 1.5 },
      { name: "whole grain bread", amount: "2 slices", price: 0.6 },
      { name: "cherry tomatoes", amount: "6", price: 0.8 },
      { name: "feta cheese", amount: "30g", price: 1.0 },
      { name: "lemon", amount: "1/2", price: 0.4 },
      { name: "red pepper flakes", amount: "1/4 tsp", price: 0.1 },
      { name: "olive oil", amount: "1 tsp", price: 0.2 },
      { name: "salt and pepper", amount: "to taste", price: 0.1 },
    ],
    instructions: [
      "Toast the bread slices until golden and crisp.",
      "Cut the avocado in half, remove the pit, and scoop the flesh into a bowl.",
      "Add a squeeze of lemon juice, salt, and pepper to the avocado and mash with a fork.",
      "Spread the mashed avocado evenly on the toast slices.",
      "Slice cherry tomatoes in half and arrange on top of the avocado.",
      "Crumble feta cheese over the tomatoes.",
      "Drizzle with olive oil and sprinkle with red pepper flakes.",
      "Serve immediately.",
    ],
    dietary: {
      vegan: false,
      vegetarian: true,
      dairyFree: false,
      glutenFree: false,
    },
  },
  {
    id: "chocolate-chip-cookies",
    name: "Chocolate Chip Cookies",
    description: "Classic homemade chocolate chip cookies that are soft in the middle and crispy on the edges.",
    image: "/placeholder.svg?height=400&width=600",
    cookTime: 25,
    servings: 24,
    ingredients: [
      { name: "all-purpose flour", amount: "280g", price: 0.5 },
      { name: "butter", amount: "225g", price: 1.8 },
      { name: "brown sugar", amount: "200g", price: 0.7 },
      { name: "white sugar", amount: "100g", price: 0.4 },
      { name: "eggs", amount: "2 large", price: 0.6 },
      { name: "vanilla extract", amount: "1 tsp", price: 0.3 },
      { name: "chocolate chips", amount: "300g", price: 2.5 },
      { name: "baking soda", amount: "1 tsp", price: 0.1 },
      { name: "salt", amount: "1/2 tsp", price: 0.05 },
    ],
    instructions: [
      "Preheat oven to 350°F (175°C) and line baking sheets with parchment paper.",
      "In a large bowl, cream together the butter, brown sugar, and white sugar until light and fluffy.",
      "Beat in the eggs one at a time, then stir in the vanilla.",
      "In a separate bowl, combine flour, baking soda, and salt.",
      "Gradually add the dry ingredients to the wet ingredients and mix until just combined.",
      "Fold in the chocolate chips.",
      "Drop rounded tablespoons of dough onto the prepared baking sheets.",
      "Bake for 10-12 minutes until edges are golden but centers are still soft.",
      "Allow cookies to cool on the baking sheet for 5 minutes before transferring to a wire rack.",
    ],
    dietary: {
      vegan: false,
      vegetarian: true,
      dairyFree: false,
      glutenFree: false,
    },
  },
  {
    id: "greek-salad",
    name: "Greek Salad",
    description: "A fresh and vibrant Mediterranean salad with crisp vegetables and tangy feta cheese.",
    image: "/placeholder.svg?height=400&width=600",
    cookTime: 15,
    servings: 4,
    ingredients: [
      { name: "cucumber", amount: "1 large", price: 0.8 },
      { name: "tomatoes", amount: "4 medium", price: 1.4 },
      { name: "red onion", amount: "1/2", price: 0.3 },
      { name: "green bell pepper", amount: "1", price: 0.8 },
      { name: "kalamata olives", amount: "100g", price: 1.5 },
      { name: "feta cheese", amount: "200g", price: 2.2 },
      { name: "olive oil", amount: "3 tbsp", price: 0.6 },
      { name: "red wine vinegar", amount: "1 tbsp", price: 0.3 },
      { name: "dried oregano", amount: "1 tsp", price: 0.2 },
      { name: "salt and pepper", amount: "to taste", price: 0.1 },
    ],
    instructions: [
      "Dice the cucumber, tomatoes, and bell pepper into bite-sized pieces.",
      "Thinly slice the red onion.",
      "Combine all vegetables in a large bowl.",
      "Add the olives.",
      "In a small bowl, whisk together olive oil, red wine vinegar, oregano, salt, and pepper.",
      "Pour the dressing over the salad and toss gently to combine.",
      "Crumble the feta cheese over the top.",
      "Serve immediately or refrigerate for up to 1 hour before serving.",
    ],
    dietary: {
      vegan: false,
      vegetarian: true,
      dairyFree: false,
      glutenFree: true,
    },
  },
]
