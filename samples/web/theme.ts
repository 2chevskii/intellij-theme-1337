interface Theme {
  name: string;
  enabled: boolean;
}

// Types, parameters, literals, and function calls.
export function describe(theme: Theme): string {
  const version = 1337;
  return theme.enabled ? `${theme.name}: ${version}` : "Disabled";
}

console.log(describe({ name: "1337 Theme", enabled: true }));
