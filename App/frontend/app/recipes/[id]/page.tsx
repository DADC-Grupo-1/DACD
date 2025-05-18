import Image from "next/image"
import Link from "next/link"
import { ArrowLeft, Clock, Users } from "lucide-react"
import { Button } from "@/components/ui/button"
import { Badge } from "@/components/ui/badge"
import { Card, CardContent } from "@/components/ui/card"
import { Separator } from "@/components/ui/separator"
import { formatCurrency } from "@/lib/utils"

// Este componente ahora es asíncrono
export default async function RecipePage({ params }: { params: { id: string } }) {
  const res = await fetch(`http://localhost:7070/recipes/${params.id}`, {
    cache: "no-store",
  })

  if (!res.ok) {
    throw new Error("Failed to fetch recipe")
  }
  const recipe = await res.json()



  function parseDietary(dietary: Record<string, any>) {
    const isTruthy = (value: any) =>
        value === true || value === "1" || value === 1 || value === "true";

    return {
      vegetarian: isTruthy(dietary.vegetarian),
      vegan: isTruthy(dietary.vegan),
      glutenFree: isTruthy(dietary.glutenFree),
      dairyFree: isTruthy(dietary.dairyFree),
    };
  }

  const dietary = parseDietary(recipe.dietary);

  const totalCost = recipe.ingredients.reduce((sum: number, ingredient: any) => sum + ingredient.price, 0)
  const costPerServing = totalCost / recipe.servings
  return (
    <div className="container mx-auto px-4 py-8">
      <div className="grid grid-cols-1 lg:grid-cols-3 gap-8">
        <div className="lg:col-span-2">
          <div className="relative aspect-video overflow-hidden rounded-lg mb-6">
            <Image src={recipe.image || "/placeholder.svg"} alt={recipe.name} fill className="object-cover" priority />
          </div>
          <h1 className="text-3xl font-bold tracking-tight mb-2">{recipe.name}</h1>
          <div className="flex flex-wrap gap-2 mb-4">
            {dietary.vegan && (
                <Badge variant="secondary" className="bg-green-100 text-green-800">Vegan</Badge>
            )}
            {!dietary.vegan && dietary.vegetarian && (
                <Badge variant="secondary" className="bg-emerald-100 text-emerald-800">Vegetarian</Badge>
            )}
            {dietary.dairyFree && (
                <Badge variant="secondary" className="bg-blue-100 text-blue-800">Dairy Free</Badge>
            )}
            {dietary.glutenFree && (
                <Badge variant="secondary" className="bg-amber-100 text-amber-800">Gluten Free</Badge>
            )}
          </div>

          <h1 className="text-3xl font-bold tracking-tight mb-2">{recipe.nameClean}</h1>

          <div className="flex items-center gap-4 text-muted-foreground mb-4">
            <div className="flex items-center">
              <Clock className="mr-1 h-4 w-4" />
              <span>{recipe.cookTime} mins</span>
            </div>
            <div className="flex items-center">
              <Users className="mr-1 h-4 w-4" />
              <span>{recipe.servings} servings</span>
            </div>
          </div>

          <p className="text-muted-foreground mb-8">{recipe.description}</p>

          <h2 className="text-xl font-semibold mb-4">Instructions</h2>
          <ol className="list-decimal pl-5 space-y-4 mb-8">
            {recipe.instructions.map((instruction, index) => (
              <li key={index} className="pl-2">
                {instruction}
              </li>
            ))}
          </ol>
        </div>

        <div>
          <Card className="sticky top-6">
            <CardContent className="pt-6">
              <h2 className="text-xl font-semibold mb-4">Recipe Cost</h2>

              <div className="flex justify-between items-center mb-2">
                <span className="text-muted-foreground">Total Cost:</span>
                <span className="font-medium">{formatCurrency(totalCost)}</span>
              </div>

              <div className="flex justify-between items-center mb-6">
                <span className="text-muted-foreground">Cost Per Serving:</span>
                <span className="font-medium">{formatCurrency(costPerServing)}</span>
              </div>

              <Separator className="mb-6" />

              <h3 className="font-medium mb-4">Ingredients</h3>
              <ul className="space-y-3 mb-6">
                {recipe.ingredients.map((ingredient, index) => (
                  <li key={index} className="flex justify-between">
                    <span>
                      {ingredient.amount} {ingredient.name}
                    </span>
                    <span className="font-medium">{formatCurrency(ingredient.price)}</span>
                  </li>
                ))}
              </ul>

              <Separator className="mb-6" />

              <div className="text-sm text-muted-foreground">
                <p>* Prices are estimates based on average market rates and may vary by location.</p>
              </div>
            </CardContent>
          </Card>
        </div>
      </div>
    </div>
  )
}
