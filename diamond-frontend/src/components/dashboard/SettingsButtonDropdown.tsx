import {
    DropdownMenu,
    DropdownMenuContent,
    DropdownMenuItem,
    DropdownMenuSeparator,
    DropdownMenuTrigger,
  } from "@/components/ui/dropdown-menu"
import { IoSettingsOutline } from "react-icons/io5"

export const SettingsButtonDropdown = () => {
return(
<DropdownMenu>
  <DropdownMenuTrigger><IoSettingsOutline className="size-5"/></DropdownMenuTrigger>
  <DropdownMenuContent>
    {/* <DropdownMenuLabel>My Account</DropdownMenuLabel> */}
    <DropdownMenuSeparator />
    <DropdownMenuItem>Settings</DropdownMenuItem>
    <DropdownMenuItem>My profile</DropdownMenuItem>
    <DropdownMenuItem className="text-red-800">Sign out</DropdownMenuItem>
  </DropdownMenuContent>
</DropdownMenu>
)
}