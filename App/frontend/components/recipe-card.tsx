import Image from "next/image"
import Link from "next/link"
import { Clock, DollarSign, Users } from "lucide-react"
import { Card, CardContent, CardFooter } from "@/components/ui/card"
import { Badge } from "@/components/ui/badge"
import { formatCurrency } from "@/lib/utils"
import { cn } from "@/lib/utils"
import type { Recipe } from "@/lib/types"

interface RecipeCardProps {
  recipe: Recipe
  smallVersion?: boolean
  matchPercentage?: number
}

export default function RecipeCard({ recipe, smallVersion = false, matchPercentage }: RecipeCardProps) {
  const totalCost = recipe.ingredients.reduce((sum, ingredient) => sum + ingredient.price, 0)

  return (
    <Card className={cn("overflow-hidden", smallVersion && "transform scale-[0.7] origin-top")}>
      <div className="relative aspect-video">
        <Image src={recipe.image || "/placeholder.svg"} alt={recipe.name} fill className="object-cover" />

        {/* Match percentage badge in top left */}
        {matchPercentage !== undefined && (
          <div className="absolute top-2 left-2 z-10">
            <Badge className="bg-primary text-primary-foreground">{matchPercentage}% match</Badge>
          </div>
        )}

        {/* Dietary badges in top right */}
        <div className="absolute top-2 right-2 flex flex-wrap gap-1 max-w-[calc(100%-16px)]">
          {recipe.dietary.vegan && (
            <Badge variant="secondary" className="bg-green-100 text-green-800 hover:bg-green-200 hover:text-green-800">
              Vegan
            </Badge>
          )}
          {!recipe.dietary.vegan && recipe.dietary.vegetarian && (
            <Badge
              variant="secondary"
              className="bg-emerald-100 text-emerald-800 hover:bg-emerald-200 hover:text-emerald-800"
            >
              Vegetarian
            </Badge>
          )}
          {recipe.dietary.dairyFree && (
            <Badge variant="secondary" className="bg-blue-100 text-blue-800 hover:bg-blue-200 hover:text-blue-800">
              Dairy Free
            </Badge>
          )}
          {recipe.dietary.glutenFree && (
            <Badge variant="secondary" className="bg-amber-100 text-amber-800 hover:bg-amber-200 hover:text-amber-800">
              Gluten Free
            </Badge>
          )}
        </div>
      </div>
      <CardContent className="p-4">
        <h3 className="font-semibold text-lg mb-2 line-clamp-1">{recipe.name}</h3>
        <p className="text-muted-foreground text-sm mb-4 line-clamp-2">{recipe.description}</p>

        <div className="flex items-center justify-between text-sm">
          <div className="flex items-center">
            <Clock className="mr-1 h-4 w-4" />
            <span>{recipe.cookTime} mins</span>
          </div>
          <div className="flex items-center">
            <Users className="mr-1 h-4 w-4" />
            <span>{recipe.servings}</span>
          </div>
        </div>
      </CardContent>
      <CardFooter className="p-4 pt-0 flex justify-between items-center">
        <div className="flex items-center text-primary">
          <DollarSign className="mr-1 h-4 w-4" />
          <span className="font-medium">{formatCurrency(totalCost)}</span>
        </div>
        <Link href={`/recipes/${recipe.id}`} className="text-sm font-medium text-primary hover:underline">
          View Recipe
        </Link>
      </CardFooter>
    </Card>
  )
}
