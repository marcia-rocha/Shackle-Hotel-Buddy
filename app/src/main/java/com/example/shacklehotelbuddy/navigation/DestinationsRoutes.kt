
sealed class DestinationRoutes(val route: String) {
    object SearchScreen: DestinationRoutes("search_screen")
    object PropertyListScreen: DestinationRoutes("property_list_screen")
}